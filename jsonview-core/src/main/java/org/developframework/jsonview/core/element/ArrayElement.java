package org.developframework.jsonview.core.element;

public class ArrayElement extends ContainerElement {

	private ObjectElement childElement;

	public ObjectElement getChildElement() {
		return childElement;
	}

	public void createChildElement() {
		childElement = new ObjectElement(super.abstractElementList);
	}

}
