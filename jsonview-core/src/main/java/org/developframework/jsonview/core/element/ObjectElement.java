package org.developframework.jsonview.core.element;

import java.util.List;

public class ObjectElement extends ContainerElement {

	public ObjectElement() {
		super();
	}

	public ObjectElement(List<AbstractElement> list) {
		super();
		super.abstractElementList.addAll(list);
	}
}
