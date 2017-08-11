package com.rms;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.rms.webservice.ReminderWebService;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		JodaMapper jodaMapper = new JodaMapper();
		jodaMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		provider.setMapper(jodaMapper);
		register(provider);
		registerClasses(ReminderWebService.class);
	}

}