package com.edsoft.framework.base;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalDriverContext {
	private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();

	public static RemoteWebDriver getRemoteWebDriver() {
		return remoteWebDriverThreadLocal.get();
	}

	static void setRemoteWebDriverThreadLocal(RemoteWebDriver driverThreadLocal) {
		remoteWebDriverThreadLocal.set(driverThreadLocal);
	}
}
