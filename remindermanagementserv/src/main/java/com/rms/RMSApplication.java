package com.rms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.rms", "com.rms.h2.model" })
public class RMSApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new RMSApplication().configure(new SpringApplicationBuilder(RMSApplication.class)).run(args);
	}
}
