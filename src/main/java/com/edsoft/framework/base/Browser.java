package com.edsoft.framework.base;

import org.openqa.selenium.WebDriver;

public class Browser {
	private WebDriver _driver;
	public BrowserType Type;

	public Browser(WebDriver _driver) {
		this._driver = _driver;
	}

	public void GoToUrl(String url) {
		_driver.get(url);
	}

	public Browser Maximize() {
		_driver.manage().window().maximize();
		return new Browser(_driver);
	}

}
