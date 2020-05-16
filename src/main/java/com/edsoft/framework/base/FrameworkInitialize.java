package com.edsoft.framework.base;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class FrameworkInitialize extends Base {
	public void initializeBrowser(BrowserType browserTypes) throws MalformedURLException {
		WebDriver driver = null;
//		RemoteWebDriver driver = null;

		switch (browserTypes) {
		case Chrome:
			System.setProperty("webdriver.chrome.driver", "C:\\marionette\\chromedriver.exe");
			driver = new ChromeDriver();
//			DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
//			driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub),capabilities);
//			LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
			break;

		case Firefox:
			System.setProperty("webdriver.firefox.marionette", "C:\\marionette\\");
			driver = new FirefoxDriver();
			break;

		case IE:
			System.setProperty("webdriver.ie.marionette", "C:\\marionette\\");
			driver = new InternetExplorerDriver();
			break;

		case Safari:
			driver = new SafariDriver();
			break;
		}
		DriverContext.setDriver(driver);
		DriverContext.Browser = new Browser(driver);
	}
}
