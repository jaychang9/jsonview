package org.developframework.jsonview.support.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.developframework.jsonview.core.DataModel;
import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.fasterxml.jackson.core.JsonGenerator;

public class JsonviewHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {

	protected JsonviewFactory jsonviewFactory;
	protected String namespace;
	protected String jsonviewid;
	protected String contentType = "application/json;charset=UTF-8";
	protected ExceptionDataBinder exceptionDataBinder;

	public JsonviewFactory getJsonviewFactory() {
		return jsonviewFactory;
	}

	public void setJsonviewFactory(JsonviewFactory jsonviewFactory) {
		this.jsonviewFactory = jsonviewFactory;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getJsonviewid() {
		return jsonviewid;
	}

	public void setJsonviewid(String jsonviewid) {
		this.jsonviewid = jsonviewid;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public ExceptionDataBinder getExceptionDataBinder() {
		return exceptionDataBinder;
	}

	public void setExceptionDataBinder(ExceptionDataBinder exceptionDataBinder) {
		this.exceptionDataBinder = exceptionDataBinder;
	}

	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
		if (exceptionDataBinder == null) {
			throw new IllegalArgumentException("Argument \"exceptionDataBinder\" is null in " + this.getClass().getName());
		}
		DataModel dataModel = new DataModel();
		exceptionDataBinder.bind(dataModel, exception);
		response.setContentType(contentType);
		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		try {
			JsonGenerator generator = jsonCreator.getObjectMapper().getFactory().createGenerator(response.getOutputStream());
			jsonCreator.printJson(generator, dataModel, namespace, jsonviewid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}
}
