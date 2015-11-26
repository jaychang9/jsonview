package org.developframework.jsonview.support.web.res;

public class JsonviewErrorResponse extends JsonviewSimpleResponse {

	public JsonviewErrorResponse(String message, String namespace, String jsonviewId) {
		super(namespace, jsonviewId);
		super.putData("success", false);
		super.putData("message", message);
	}

}
