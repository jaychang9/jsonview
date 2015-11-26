package org.developframework.jsonview.core.element;

import java.util.HashMap;

public class JsonviewConfiguration extends HashMap<String, JsonviewPackage> {

	private static final long serialVersionUID = 4516452283101301938L;

	private boolean isUnderlineMode = true;

	public void push(JsonviewPackage jsonviewPackage) {
		super.put(jsonviewPackage.getNamespace(), jsonviewPackage);
	}

	public Jsonview extractJsonview(String namespace, String id) {
		return super.get(namespace).get(id);
	}

	public boolean isUnderlineMode() {
		return isUnderlineMode;
	}

	public void setUnderlineMode(boolean isUnderlineMode) {
		this.isUnderlineMode = isUnderlineMode;
	}

}
