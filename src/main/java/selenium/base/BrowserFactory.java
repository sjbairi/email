package selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.reports.Log;

public class BrowserFactory extends Base{

	public WebDriver createBrowserInstance(String browser) {
		
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				Log.info("Chrome browser is initiated and will be start running");
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				Log.info("Firefox browser is initiated and will be start running");
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				Log.info("Edge browser is initiated and will be start running");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

}
