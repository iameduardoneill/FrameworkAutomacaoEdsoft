package com.edsoft.framework.controls.elements;

import com.edsoft.framework.controls.api.ImplementedBy;
import com.edsoft.framework.controls.internals.Control;
import com.edsoft.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

	void PerformClick();

	String GetButtonText();

	void PerformSubmit();

	ControlBase Wait();

	ControlBase WaitForVisible();

	ControlBase Click();

	ControlBase ScrollToElement();

}
