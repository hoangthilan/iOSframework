package coccocmap.framework;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import coccoc.selenium.Framework.CommonController;

public class TestLogger {

	/**
	 * This class user to log everything which happens when run test cases .
	 * 
	 * @author havtt
	 * @param message  which will be displayed to user when run test case
	 * @param level  which can be like Trace , debug , warn, error
	 * @return void
	 */
	public static void log(String message, Level level) {
		PropertyConfigurator.configure("log4j.properties");
		Throwable t = new Throwable();
		String logMessage = message;
		StackTraceElement[] elements = t.getStackTrace();
		Logger logger = Logger.getLogger(coccocmap_framework.class);
		String Filename = elements[2].getFileName();
		String sClassName = Filename.substring(0, Filename.length() - 5); // remove
																			// .java
		String sMethodName = elements[2].getMethodName();
		logMessage = String.format(message, "[%-10s][%s] %s", sClassName, sMethodName);
		logger.log(level, logMessage);

	}

	/**
	 * log everything when run testcase in TRACE level
	 * 
	 * @author havtt
	 * @param message which will be displayed to user when run test case 
	 * @return void 
	 */  
	public static void trace(String message) { 
		System.out.println("TRACE: " + message);
		log(message, Level.TRACE);
	}

	/**
	 * log everything when run testcase in DEBUG level
	 * 
	 * @author havtt
	 * @param message
	 *            which will be displayed to user when run test case
	 * @return void
	 */
	public static void debug(String message) {
		System.out.println("DEBUG POINT: " + message);
		log(message, Level.DEBUG);
	}

	/**
	 * log everything when run testcase in INFO level
	 * 
	 * @author havtt
	 * @param message
	 *            which will be displayed to user when run test case
	 * @return void
	 */
	public static void info(String message) {
		System.out.println("INFO: " + message);
		log(message, Level.INFO);
	}

	/**
	 * log everything when run testcase in WARN level
	 * 
	 * @author havtt
	 * @param message
	 *            which will be displayed to user when run test case
	 * @return void
	 */
	public static void warn(String message) {
		System.out
				.println("-------------------------------------------------------------------");
		System.out.println("WARNING: " + message);
		System.out
				.println("-------------------------------------------------------------------");
		log(message, Level.WARN);
	}

	/**
	 * log everything when run testcase in ERROR level
	 * 
	 * @author havtt
	 * @param message
	 *            which will be displayed to user when run test case
	 * @return void
	 */
	public static void error(String message) {
		System.out.println("ERROR: " + message);
		log(message, Level.ERROR);
	}

	public static void setTestcaseStatus() {
		info("Set testcase satus");
	}
}
