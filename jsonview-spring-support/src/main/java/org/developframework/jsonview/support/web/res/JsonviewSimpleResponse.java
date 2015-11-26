package org.developframework.jsonview.support.web.res;

import org.developframework.jsonview.core.DataModel;

public class JsonviewSimpleResponse extends JsonviewResponse {

	public JsonviewSimpleResponse(String namespace, String jsonviewId) {
		super(new DataModel(), namespace, jsonviewId);
	}

}
