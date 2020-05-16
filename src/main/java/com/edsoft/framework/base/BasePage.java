package com.edsoft.framework.base;

public abstract class BasePage extends Base {

	public <TPage extends BasePage> TPage As(Class<TPage> pageInstance) {
		try {
			return (TPage) this;
		} catch (Exception e) {
			System.out.println("erro: " + e.getStackTrace());
		}
		return null;
	}
}
