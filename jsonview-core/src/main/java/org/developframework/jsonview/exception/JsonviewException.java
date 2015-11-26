package org.developframework.jsonview.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonviewException extends RuntimeException {

	private static final long serialVersionUID = 1748720914783312577L;
	private static final Logger log = LoggerFactory.getLogger("org.developframework.jsonview");

	public JsonviewException(String message) {
		super(message);
	}

	public JsonviewException(String message, Throwable e) {
		super(message, e);
		log.error(message, e);
	}
}
