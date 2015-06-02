package com.fairdeal.notify.email;

import java.util.Map;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;

@Component(value = "mailService")
public class CustomMailSender extends JavaMailSenderImpl {

	private boolean isInitalized;
	private String prefix = "dlm.email.";

	@Autowired
	private TemplateEngine templateEngine;

	public void init() {
		if (isInitalized() == false) {
			setHost(Config.getString(prefix + "host"));
			setPort(Config.getInt(prefix + "port"));
			setUsername(Config.getString(prefix + "username"));
			setPassword(Config.getString(prefix + "password"));
			setJavaMailProperties(getEmailProperties());
			setInitalized(true);
		}
	}

	private Properties getEmailProperties() {
		String[] propertiesConfig = Config.getStringArray(prefix + "properties");
		Properties properties = new Properties();
		for (String tempProperty : propertiesConfig) {
			String key = Config.getString(prefix + "properties." + tempProperty + ".key");
			String value = Config.getString(prefix + "properties." + tempProperty + ".value");
			properties.setProperty(key, value);
		}
		return properties;
	}

	private Session getPPSession() {
		Session session = Session.getInstance(getJavaMailProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getUsername(), getPassword());
			}
		});
		return session;
	}

	public boolean isInitalized() {
		return isInitalized;
	}

	public void setInitalized(boolean isInitalized) {
		this.isInitalized = isInitalized;
	}

	public void sendEmail(String template, String to, String from, String subject, Map objectMap) {
		String[] tos = { to };
		sendEmail(template, tos, Config.getString(prefix + "default.from"), subject, objectMap);
	}

	public void sendEmail(String template, String[] to, String from, String subject, Map objectMap) {
		try {
			MimeMessage message = createMimeMessage();
			final MimeMessageHelper messages =new MimeMessageHelper(message, true, "UTF-8"); 
			messages.setTo(to);
			messages.setSubject(subject);
			String body=getTemplateHTML(template, objectMap);
			messages.setText(body,true);
			LoggerUtil.debug(" Sedningemail to:" + to[0] + " with email template as:" + template+" Subject as :"+subject+" and body"+body);
			if (Config.getString("dlm.email.send").equals("prod"))
				send(message);
		} catch (Exception e) {
			LoggerUtil.error("Exception is caused while sending email", e);
		}
	}

	public void sendEmail(String template, String to, String subject, Map objectMap) {
		sendEmail(template, to, Config.getString(prefix + "default.from"), subject, objectMap);
	}

	public void sendEmail(String template, String to, String subject) {
		sendEmail(template, to, subject, null);
	}

	private String getTemplateHTML(String templateName, Map<String, Object> objectMap) {
		if (templateName == null) {
			return "This is a test email";
		}
		Context context = new Context();
		if (objectMap != null)
			for (String key : objectMap.keySet()) {
				context.setVariable(key, objectMap.get(key));
			}
		String result = templateEngine.process(Config.getString("dlm.email.template.location") + templateName, context);
		return result;
	}

}
