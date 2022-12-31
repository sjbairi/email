package selenium.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import selenium.reports.ExtentReportManager;
import selenium.reports.Log;

public class Base {
	public static WebDriver driver;
	public static Properties property;
	public static String time = getCurrentTime();
	public static String concat = ".";
	public static String reportPath = "./reports/report" + time + "/";

	BrowserFactory browserfactory;

	@org.testng.annotations.BeforeSuite
	public static Properties initiateProperies() {
		property = new Properties();
		try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "./config.properties")) {
			property.load(fis);
			Log.info("Config file has been loaded sucessfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property;
	}

	@BeforeTest
	public void initateExtentReport() throws Exception {
		ExtentReportManager.setExtentReport();
		Reporter.log("Extent Report initialize", true);
		Log.info("Initiating Extent Report");
		property.setProperty("dateTime", time);
		property.store(new FileWriter("config.properties"), "");
	}

	@BeforeMethod
	public void launchBrowser() {
		browserfactory = new BrowserFactory();
		browserfactory.createBrowserInstance(property.getProperty("browser"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(property.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() {
		 driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		Reporter.log("Terminating extend report class", true);
		Log.info("Extent Report class is closed");
		ExtentReportManager.endReport();

	}

	public static String screenShot(WebDriver driver, String filename) throws IOException {

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = "./reports/report" + time + "/Screenshots/" + filename + "_" + getCurrentTime() + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String screenShot(WebDriver driver) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		return takesScreenshot.getScreenshotAs(OutputType.BASE64); 
	/*	String imageBase64 = null;
		try {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(driver);
			ByteArrayOutputStream out = new  ByteArrayOutputStream(); 
			ImageIO.write(screenshot.getImage(),"PNG", out);
			byte[] bytes = out.toByteArray();
			return imageBase64 = Base64.encodeBase64String(bytes);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return imageBase64;  */
	}

	public static String getCurrentTime() {
		Date date = new Date();
		String currentTime = new SimpleDateFormat("yyyy-MM-dd kkmm").format(date);
		Reporter.log(currentTime, true);
		return currentTime;
	}

}
