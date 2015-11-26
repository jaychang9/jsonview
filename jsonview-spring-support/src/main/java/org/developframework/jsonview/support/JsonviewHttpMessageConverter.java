package org.developframework.jsonview.support;

import java.io.IOException;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.support.web.res.JsonviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonviewHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	@Autowired
	protected JsonviewFactory jsonviewFactory;

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		JsonviewResponse jsonviewResponse = (JsonviewResponse) object;
		JsonCreator creator = jsonviewFactory.getJsonCreator(super.objectMapper);
		JsonEncoding encoding = super.getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator generator = super.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
		creator.printJson(generator, jsonviewResponse.getDataModel(), jsonviewResponse.getNamespace(), jsonviewResponse.getJsonviewId());
	}

}
