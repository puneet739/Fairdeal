package com.fairdeal.bean;

import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.fairdeal.Constants;
import com.fairdeal.util.LoggerUtil;

@Component(value = "testBean")
@Scope
public class TestBean {

	private String text;

	@PreAuthorize(value="hasRole('ROLE_USER')")
	public void doSomething() {
		LoggerUtil.debug("Calling from this method");
		throw new NullPointerException("A NullPointerException");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	public void checkDetails(AjaxBehaviorEvent e) {
		System.out.println("AjaxBehavior Listener :: " + e.getBehavior() + " :: " + e.getSource());
		doSomething();
		throw new NullPointerException("A NullPointerException");
	}
}
