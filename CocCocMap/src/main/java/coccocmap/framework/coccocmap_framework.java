package coccocmap.framework;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import android.content.res.Resources;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class coccocmap_framework {

	public static IOSDriver<WebElement> driver;
	public static DesiredCapabilities cap; 



	/**
	 * Destroy the ios driver instance (Close application). Uninstall Cốc
	 * Cốc Map application
	 * 
	 * @author lanht
	 * @param driver
	 *            which is a object of AppiumDriver
	 * @return : void
	 * @date 02 Feb 2017
	 *
	 */
	public void teardown() {
		driver.removeApp("com.itim.nhanha");
		driver.quit();
	}

		/**
		 * Install package ipa
		 * 
		 * @author lanht
		 * @param filepath
		 *            file path which exists package ios
		 * @param filename
		 *            file name of package ios
		 * @param platformName
		 *            ex: IOS
		 * @param PLATFORM_NAME
		 *            version of device's ios ex: iOS
		 * @return void
		 * @throws Exception
		 * @date 02 Feb 2017
		 */
		public void setupiOS(String filepath, String filename, String PLATFORM_VERSION, String PLATFORM_NAME, String DEVICE_NAME, String UIID) throws Exception {
			cap = new DesiredCapabilities();
			File appDir = new File(filepath);
			File app = new File(appDir, filename);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
			cap.setCapability(MobileCapabilityType.UDID, UIID); 
			if (app.exists()) {
				cap.setCapability(MobileCapabilityType.APP, app.getCanonicalPath());
				System.out.println("package of Coc Coc Map app exists");
			} else {
				//System.out.println(new File("../CocCocMap/src/main/resources/apps/NhaNhaApp-dev.app").getCanonicalPath());
				System.out.println("package of Coc Coc Map app doesn't exist");
			}
			//Enable unicode keyboard
			//cap.setCapability("unicodeKeyboard", true);
			//cap.setCapability("resetKeyboard", true);
			driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		}

	/**
	 * wait for Element present
	 * 
	 * @author lanht
	 * @param locator,
	 *            which belong to By class . User can call static functions on
	 *            By to find element based on criteria like name,
	 *            id,class,...etc ..
	 * @param timeToWaitInSeconds
	 *            time to wait inseconds
	 * @return : void
	 * @date 02 Feb 2017
	 */
	public static void waitForElementPresent(final By locator, int timeToWaitInSeconds) {
		try {
			new WebDriverWait(driver, timeToWaitInSeconds).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return driver.findElement(locator);
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("There was an issue in finding the WebElement " + locator + "."
					+ e.getMessage().split("waiting")[0]);
		}
	}

	/**
	 * Insert value into TextField or TextArea , verifying TextField By class
	 * 
	 * @author lanht
	 * @param driver,which
	 *            contain web elements.
	 * @param webLocator,
	 *            which belong to By class . User can call static functions on
	 *            By to find element based on criteria like name,
	 *            id,class,...etc ..
	 * @param Value,
	 *            which will be inserted into Textfield or TextArea .
	 * @return void
	 * @date 02 Feb 2017
	 */
	public void typeText(By webLocator, String Value) {
		WebElement textfield = driver.findElement(webLocator);
		if (textfield == null) {
			System.out.println(" textfield is NULL , thus, can't perform insert action on it ");
		} else {
			textfield.clear();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			textfield.sendKeys(Value);
			System.out.println("Insert successfully value " + Value);
		}

	}

	/**
	 * click on Element
	 * 
	 * @author lanht
	 * @param webLocator,e
	 *            which belong to By class . User can call static functions on
	 *            By to find element based on criteria like name,
	 *            id,class,...etc ..
	 * @return void
	 * @throws Exception
	 * @date 02 Feb 2017
	 */

	public void clickOnElement(By webLocator) {

		try {
			WebElement element = driver.findElement(webLocator);
			waitForElementPresent(webLocator, 120);
			element.click();

		} catch (Exception e) {
			System.out.println("ERROR: " + e.toString());
		}

	}

	/**
	 * clear value into TextField or TextArea , verifying TextField By class
	 * 
	 * @author lanht
	 * @param driver,which
	 *            contain web elements.
	 * @param webLocator,
	 *            which belong to By class . User can call static functions on
	 *            By to find element based on criteria like name,
	 *            id,class,...etc ..
	 * @return void
	 * @date 02 Feb 2017
	 */
	public void clearText(By webLocator) {
		WebElement textfield = driver.findElement(webLocator);
		textfield.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// TestLogger.info("Clear all value");
	}


	/**
	 * return to Main screen of device
	 * 
	 * @author lanht
	 * @return void
	 * @date 02 Feb 2017
	 */
	public void returnMainScreen() {
		driver.getKeyboard().sendKeys(Keys.HOME);
	}

		/**
		 * Check an elenment is Present or Not
		 * 
		 * @author lanht
		 * @date 02 Feb 2017
		 * @param driver
		 *            ,represent like website mamager
		 * @param locator
		 *            belong to By class and reponsible for locating element based
		 *            on criteria like name , id, class of element
		 * @return void
		 */
		public void isElementPresent(By locator) {
			List<WebElement> p = driver.findElements(locator);
			
			if (p.size() != 0) {
				TestLogger.info("ELEMENT"+ locator + "FOUND");
			} else
				TestLogger.warn("ELEMENT" + locator + "NOT FOUND");
		}
		/**
		 * Capture current screen
		 * 
		 * @author lanht
		 * @return void
		 * @exception IOException
		 * @date 02 Feb 2017
		 */
		public void screenshot() throws IOException {
			System.out.println("Capturing the snapshot of the page ");
			cap.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
			File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String FileName = UUID.randomUUID().toString();
			File targetFile = new File("target/screenshorts/" + FileName + ".jpg");
			FileUtils.copyFile(srcFiler, targetFile);
			System.out.println(targetFile);
		}

	/**
	 * close Application has installed from task manager
	 * 
	 * @author lanht
	 * @return void
	 * @date 02 Feb 2017
	 */
	public void closeAppHasInstalledFromTaskManager() {
		driver.getKeyboard().sendKeys(Keys.HOME);
		driver.getKeyboard().sendKeys(Keys.HOME);
	}

	/**
	 * get Text of an element By locator
	 * 
	 * @author lanht
	 * @return String
	 * @throws Exception
	 * @date 02 Feb 2017
	 */
	public String textAt(By locator) throws Exception {
		String text = null;
		try {
			waitForElementPresent(locator, 3);
			text = driver.findElement(locator).getText().trim();
		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
		return text;
	}

	/**
	 * get Text of an element By locator and compare with verificationText
	 * 
	 * @author lanht
	 * @return boolean
	 * @throws Exception
	 * @date 02 Feb 2017
	 */
	public boolean textAtContains(By locator, String verificationText) {
		boolean isTextMatched = false;
		try {
			String temp = textAt(locator).toLowerCase();
			if (temp.length() < 1)
				return isTextMatched;
			isTextMatched = temp.contains(verificationText.toLowerCase())
					|| verificationText.toLowerCase().contains(temp);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return isTextMatched;
	}

	/**
	 * get current time
	 * 
	 * @author lanht
	 * @return String
	 * @date 02 Feb 2017
	 */
	public String getCurrentTime() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		return currentTime;
	}

	/**
	 * get width screen
	 * 
	 * @author lanht
	 * @return int
	 * @date 02 Feb 2017
	 */
	public static int getScreenWidth() {
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	/**
	 * get Height screen
	 * 
	 * @author lanht
	 * @return int
	 * @date 02 Feb 2017
	 */
	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}
	/**
	 * get current time
	 * 
	 * @author lanht
	 * @return String
	 * @date 02 Feb 2017
	 */
	public String getCurrentTimes() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}
}
