package selenium.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import selenium.base.Base;
import selenium.reports.ExtentReportManager;
import selenium.reports.Log;

public class utilities extends Base {
	Actions action = new Actions(driver);
	Select select;

	public void lowWait() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			Log.error(e.getMessage());
		}
	}

	public void highWait() {
		try {
			TimeUnit.MILLISECONDS.sleep(2500);
		} catch (InterruptedException e) {
			Log.error(e.getMessage());
		}
	}

	public void sendKeys(String element, String text) {
		try {
			driver.findElement(By.xpath(element)).sendKeys(text);
			Log.info("Webelement is found and able to type the following text message :" + text);
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			ExtentReportManager.test.log(Status.FAIL, "Webelement is not found" + e.getMessage());
			Assert.fail();
		}
	}

	public void sendKeys(WebElement element, String text) {
		try {
			element.sendKeys(text);
			ExtentReportManager.test.log(Status.PASS, "Text message is send sucessfully " + text);
			Log.info(element.toString() + " Webelement is found sucessfully and send the message :" + text);
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			ExtentReportManager.test.log(Status.FAIL, "Unable to sendkeys and type the text " + e);
			Assert.fail();
		}

	}

	public void MouseOver(WebElement MouseOverElement, WebElement targetElement) throws InterruptedException {
		try {
			action.moveToElement(MouseOverElement).build().perform();
			targetElement.click();
			ExtentReportManager.test.log(Status.PASS, "Sucessfully mousehover and selected " + targetElement);
			Log.info(MouseOverElement.toString() + " and " + targetElement.toString()
					+ " Webelements  are found sucessfully ");
		} catch (Exception e) {
			Log.error(e.getMessage());
			ExtentReportManager.test.log(Status.FAIL, "Unable to mousehover" + e);
			Assert.fail();
			e.printStackTrace();
		}
	}

	public void MouseOver(String mouseOverElement, String targetElement) {
		try {
			action.moveToElement(driver.findElement(By.xpath(mouseOverElement))).build().perform();
			Thread.sleep(100);
			ExtentReportManager.test.log(Status.PASS, "Sucessfully mouse hover on " + mouseOverElement);
			driver.findElement(By.xpath(targetElement)).click();
			ExtentReportManager.test.log(Status.PASS, "Sucessfully mousehover and selected " + targetElement);
			Log.info("Sucessfully mouse hover and select the webelements");
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			ExtentReportManager.test.log(Status.FAIL, "Unable to mousehover" + e);
			Assert.fail();
			e.printStackTrace();
		}
	}

	public void selectDropDownByIndex(WebElement element, int indexValue) {
		select = new Select(element);
		try {
			select.selectByIndex(indexValue);
			ExtentReportManager.test.log(Status.PASS,
					"Sucessfully selected the dropdown element based on index " + indexValue);
			Log.info("Located select dropdown Webelement: " + element.toString() + " with indexvalue " + indexValue);
		} catch (Exception e) {
			Log.error(e.getMessage());
			ExtentReportManager.test.log(Status.FAIL, "Unable to select element from dropdown " + indexValue);
			Assert.fail();
		}
	}

	public void selectDropDownByValue(WebElement element, String value) {
		select = new Select(element);
		try {
			select.selectByValue(value);
			ExtentReportManager.test.log(Status.PASS, "Sucessfully selected the element based on value " + value);
			Log.info("Located select dropdown Webelement: " + element.toString() + " with value " + value);
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			ExtentReportManager.test.log(Status.FAIL, "Unable to select element from dropdown " + value);
			Assert.fail();
		}
	}

	public void selectDropDownByVisiable(WebElement element, String text) {
		select = new Select(element);
		try {
			select.selectByVisibleText(text);
			ExtentReportManager.test.log(Status.PASS,
					"Sucessfully selected the dropdown element based on text " + text);
			Log.info("Located select dropdown Webelement: " + element.toString() + " with visiblie " + text);
		} catch (Exception e) {
			Log.error(e.getMessage());
			ExtentReportManager.test.log(Status.FAIL, "Unable to select element from dropdown " + text);
			Log.error(e.getMessage());
		}
	}

	public void sendKeys(By id, String text) {
		try {
			driver.findElement(id).sendKeys(text);
			ExtentReportManager.test.log(Status.PASS, " Webelement is found sucessfully and send the message: " + text);
			Log.info(id.toString() + " Webelement is found sucessfully and send the message: " + text);
		} catch (Exception e) {
			Log.error(e.getMessage());
			ExtentReportManager.test.log(Status.FAIL, "Unable to mouse hover and select the element " + e);
			Log.error(e.getMessage());
		}
	}

	public void Click(By id) {
		try {
			driver.findElement(id).click();
			ExtentReportManager.test.log(Status.PASS, "Webelement " + id + "is found and click on it sucessfully ");
			Log.info(id.toString() + " Webelement is found and click on it sucessfully");
		} catch (Exception e) {
			Log.error(e.getMessage());
			ExtentReportManager.test.log(Status.FAIL, " Unable to click on element " + e.getMessage());
			Log.error(e.getMessage());
		}
	}

	public void Click(String element) {
		try {
			driver.findElement(By.xpath(element)).click();
			ExtentReportManager.test.log(Status.PASS,
					"Webelement is found and click on it sucessfully " + element.toString());
			Log.info("Sucessfully clicked on Click webelement " + element.toString());
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			ExtentReportManager.test.log(Status.FAIL, " Unable to click on element " + e.getMessage());
			Assert.fail();
		}
	}

	public boolean isDisplayed(String element) {
		boolean ele = false;
		try {
			ele = driver.findElement(By.xpath(element)).isDisplayed();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return ele;
	}

	public void clear(String element) {
		try {
			driver.findElement(By.xpath(element)).clear();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	public void scrollDowntoElement(String element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement scrolltoElement = driver.findElement(By.xpath(element));
			js.executeScript("arguments[0].scrollIntoView();", scrolltoElement);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	public static String text = null;

	public String getText(String element) {
		try {
			text = driver.findElement(By.xpath(element)).getText();
			return text;
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return text;
	}
}
