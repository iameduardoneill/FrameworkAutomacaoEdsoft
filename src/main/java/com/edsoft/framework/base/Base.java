package com.edsoft.framework.base;

import com.edsoft.framework.controls.api.ControlFactory;
public class Base {

    public static BasePage CurrentPage;

    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page)
    {
        Object obj = ControlFactory.initElements(DriverContext.Driver, page);
        return page.cast(obj);
    }
}
