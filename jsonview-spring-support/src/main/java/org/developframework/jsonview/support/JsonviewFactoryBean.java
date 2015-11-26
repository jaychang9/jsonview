package org.developframework.jsonview.support;

import org.developframework.jsonview.core.JsonviewFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonviewFactoryBean {

	private JsonviewFactory jsonviewFactory;

	public JsonviewFactoryBean(String config) {
		this(new String[]{config});
	}

	public JsonviewFactoryBean(String[] configs) {
		jsonviewFactory = new JsonviewFactory(configs);
	}

	public JsonviewFactory getJsonviewFactory() {
		return jsonviewFactory;
	}
}
