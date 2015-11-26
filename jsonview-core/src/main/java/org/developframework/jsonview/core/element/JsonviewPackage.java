package org.developframework.jsonview.core.element;

import java.util.HashMap;

public class JsonviewPackage extends HashMap<String, Jsonview> {

	private static final long serialVersionUID = -3600757107255951758L;

	private String namespace;

	public void push(Jsonview jsonview) {
		super.put(jsonview.getId(), jsonview);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
