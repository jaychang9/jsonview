package org.developframework.jsonview.support.web.res;

import org.developframework.jsonview.core.DataModel;

public abstract class JsonviewResponse {

	protected DataModel dataModel;
	protected String namespace;
	protected String jsonviewId;

	public JsonviewResponse(DataModel dataModel, String namespace, String jsonviewId) {
		this.dataModel = dataModel;
		this.namespace = namespace;
		this.jsonviewId = jsonviewId;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public String getNamespace() {
		return namespace;
	}

	public String getJsonviewId() {
		return jsonviewId;
	}

	public void putData(String key, Object data) {
		dataModel.put(key, data);
	}

}
