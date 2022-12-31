package selenium.reports;

import java.net.InetAddress;

import org.apache.commons.lang3.SystemUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import selenium.base.Base;

public class ExtentReportManager extends Base {
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentReports setExtentReport() throws Exception {
		ExtentFactory.getInstance().getExtent();
		htmlReporter = new ExtentSparkReporter(reportPath +"HTML Report"+ Base.time + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.loadXMLConfig("./extent-report.xml");
		String systemName = null;
		
		try {
			systemName = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			Log.error(e.toString());
		}

		extent.setSystemInfo("HostName", systemName);
		extent.setSystemInfo("OS", SystemUtils.OS_NAME.toUpperCase());
		extent.setSystemInfo("OS", SystemUtils.USER_NAME.toUpperCase());
		extent.setSystemInfo("Browser", Base.property.getProperty("browser").toUpperCase());
		return extent;
	}

	public static void endReport() {
		extent.flush();
	}
}
