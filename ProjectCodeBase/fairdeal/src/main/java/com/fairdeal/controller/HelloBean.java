package com.fairdeal.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

@ManagedBean
@RequestScoped
public class HelloBean implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String submit="welcome";

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String submit() {
        	System.out.println("Calling the submit button");
        	return "welcome";
		}

		public String getSubmit() {
			return submit;
		}
		

		public void setSubmit(String submit) {
			this.submit = submit;
		}
}
