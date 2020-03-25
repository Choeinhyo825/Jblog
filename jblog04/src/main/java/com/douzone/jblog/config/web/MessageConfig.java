package com.douzone.jblog.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/douzone/jblog/config/web/properties/messages_ko");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
