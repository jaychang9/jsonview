package org.developframework.jsonview.core.element;

public class ImportElement extends AbstractElement {

	private String namespace;
	private String id;

	public ImportElement(String namespace, String id) {
		this.namespace = namespace;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
