package org.developframework.jsonview.support.web.res;

public class JsonviewSuccessResponse extends JsonviewSimpleResponse {

	public JsonviewSuccessResponse(String namespace, String jsonviewId) {
		super(namespace, jsonviewId);
		super.putData("success", true);
	}

}
