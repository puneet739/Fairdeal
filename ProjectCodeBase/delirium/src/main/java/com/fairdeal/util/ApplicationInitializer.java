package com.fairdeal.util;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fairdeal.notify.email.CustomMailSender;

@Component(value="applicationInitializer")
public class ApplicationInitializer implements ApplicationListener<ContextRefreshedEvent>,Serializable{

	@Autowired
	CustomMailSender mailSender;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		init();
	}

	private void init() {
		mailSender.init();
	}
	
}
