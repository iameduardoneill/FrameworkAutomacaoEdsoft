package com.edsoft.framework.utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.edsoft.framework.base.DriverContext;

public class StoredActions {
	private String dataFormatada;
	private String horaFormatada;
	private static final Integer DEFAULTPOLLINGSECONDS = 1;
	private static final Integer DEFAULTTIMEOUTSECONDS = 10;


	/*********************** ESPERAS ***************************/
	public static StoredActions sleep(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
		return new StoredActions();
	}
	
	/*********************** DATE ***********************************/
	public static String getDataFormatada() {
		LocalDate data = LocalDate.now();
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String dataFormatada = formatterData.format(data);
		return dataFormatada;
	}

	public static String getHoraFormatada() {
		LocalTime hora = LocalTime.now();
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaFormatada = formatterHora.format(hora);
		return horaFormatada;
	}

	/******************** MANIPULAÇÃO DE GUIADRIVER **************************/
	public static StoredActions driverManage() {
		for (String winHandle : DriverContext.Driver.getWindowHandles()) {
			DriverContext.Driver.switchTo().window(winHandle);
		}
		return new StoredActions();
	}

	/********************** SELECT COMBO **************************/
	public static StoredActions selectElementByVisibleText(WebElement element, String text) {
		try {
			element.click();
			Select drop = new Select(element);
			drop.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("Error select element from text!!!");
		}
		return new StoredActions();
	}

	/******************* JAVASCRIPTEXECUTOR **************************/
	private static Object executeJs(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) DriverContext.Driver;
		return js.executeScript(script, args);
	}

	public static StoredActions scrollToElement(WebElement element) {
		executeJs("Arguments[0].scrollIntoView(true)", element);
		return new StoredActions();
	}

	public static StoredActions scroll(int scroll) {
		executeJs("window.scrollBy(0," + scroll + ")", "");
		return new StoredActions();
	}

	/*******************WEBELEMENT*********************/
	public void clickComumSelenium(WebElement element) {
		element.click();
	}

	public void click(WebElement element) throws InterruptedException {
		try {
			executeJs("arguments[0].click", element);
		} catch (StaleElementReferenceException e) {
			this.clickComumSelenium(element);
		} catch (WebDriverException we) {
			this.sleep(2);
			this.clickComumSelenium(element);
		}
	}

	public void insertText(WebElement element, String value) {
		this.fluentlyWaitUtilVisibility(element).sendKeys(value);
	}

	public void clearAndInsertText(WebElement element, String value) {
		this.fluentlyWaitUtilVisibility(element);
		element.clear();
		element.sendKeys(value);
	}

	public String getText(WebElement element) {
		return this.fluentlyWaitUtilVisibility(element).getText();
	}

	public void elementSubmit(WebElement element) {
		element.submit();
	}

	public void selectElementByIndex(WebElement element, int index) {
		try {
			Select drop = new Select(this.fluentlyWaitUtilVisibility(element));
			drop.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("Error select element from index!!!");
		}
	}

	public void selectElementByValue(WebElement element, String value) {
		try {
			Select drop = new Select(this.fluentlyWaitUtilVisibility(element));
			drop.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Error select element from value: " + e.getMessage());
		}
	}

	public void selectRadioFromList(List<WebElement> listElement, int option) throws InterruptedException {
		try {
			if (option >= 0 && option <= listElement.size()) {
				this.click(listElement.get(option));
			}
		} catch (Exception e) {
			System.out.println("option not exist or element not located: " + e.getMessage());
		}
	}

	public void moveToElement(WebElement element) {
		new Actions(DriverContext.Driver).moveToElement(this.fluentlyWaitUtilVisibility(element));
	}

	public void moveToElementAndClick(WebElement element) {
		new Actions(DriverContext.Driver).moveToElement(element).click();
	}

	public Boolean elementIsClickable(WebElement element, Integer seconds) {
		try {
			return this.waitForElementToBeClickable(element, seconds) != null;
		} catch (NoSuchElementException e) {
			System.out.println("Elemento nao visivel (NoSuchElementException).");
			return false;
		} catch (StaleElementReferenceException e) {
			System.out.println("Elemento nao visivel (StaleElementReferenceException).");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private WebElement fluentlyWaitUtilVisibility(WebElement element) {
		return (new FluentWait<WebDriver>(DriverContext.Driver)).withTimeout(Duration.ofSeconds(DEFAULTTIMEOUTSECONDS))
				.pollingEvery(Duration.ofSeconds(DEFAULTPOLLINGSECONDS)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(element));
	}

	private WebElement waitForElementToBeClickable(WebElement element, int seconds) {
		return new WebDriverWait(DriverContext.Driver, seconds).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
	}
}
