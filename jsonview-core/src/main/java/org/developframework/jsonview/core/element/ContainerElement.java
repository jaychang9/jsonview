package org.developframework.jsonview.core.element;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.developframework.jsonview.contrants.ContainerElementType;
import org.developframework.jsonview.utils.U;

public abstract class ContainerElement extends AbstractElement {

	protected List<AbstractElement> abstractElementList;
	protected ContainerElementType type;

	public ContainerElement() {
		this.abstractElementList = new LinkedList<AbstractElement>();
	}

	public void addElement(AbstractElement element) {
		abstractElementList.add(element);
	}

	public Iterator<AbstractElement> elementIterator() {
		return abstractElementList.iterator();
	}

	public boolean isEntity() {
		return type == ContainerElementType.ENTITY;
	}

	public boolean isVirtual() {
		return type == ContainerElementType.VIRTUAL;
	}

	public void setType(String type) {
		this.type = U.isValid(type) && type.equals("virtual") ? ContainerElementType.VIRTUAL : ContainerElementType.ENTITY;
	}

	public boolean isElementEmpty() {
		return abstractElementList.isEmpty();
	}

}
