package com.fairdeal.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ManagedBean(name="helloWorldBean")
@ViewScoped
public class HelloWorldBean  implements Serializable{
	
	private String hello;
	
	public String getHello() {
		return "Hello from PrimeFaces and Spring Boot!";
	}
}