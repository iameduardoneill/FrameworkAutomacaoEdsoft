package com.edsoft.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.edsoft.framework.configs.Settings;

public class DriverContext {

	public static WebDriver Driver;
	public static Browser Browser;

	public static void setDriver(WebDriver driver) {
		Driver = driver;
	}

	public static void WaitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverContext.Driver;
		ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) DriverContext.Driver)
				.executeScript("return document.readyStates").toString().equals("complete");
		boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		if (!jsReady)
			wait.until(jsLoad);
		else
			Settings.Logs.Write("Page is ready");
	}

	public static void WaitForElementVisible(final WebElement elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		wait.until(ExpectedConditions.visibilityOf(elementFindBy));
		Settings.Logs.Write("Wait visiblity element");
	}

	public static void WaitUntilTextDisplayed(final By element, String text) {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		wait.until(textDisplayed(element, text));
	}

	public static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, final String text) {
		return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
	}

	public static void WaitElementEnabled(final By elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
	}

	public static void WaitForElementTextVisible(final WebElement elementFindBy, String text) {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
		Settings.Logs.Write("Wait visiblity element text");
	}
	
	public static void WaitUntilElementClicked(final By elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(elementFindBy)).click();
		Settings.Logs.Write("Clicrable Dialog");
	}
}
