package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.AbstractElement;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractProcessor<ELEMENT extends AbstractElement, NODE extends JsonNode> {

	protected Context context;
	protected ELEMENT currentElement;
	protected NODE currentNode;
	protected Object currentObject;

	public AbstractProcessor(Context context, ELEMENT currentElement, NODE currentNode) {
		this.context = context;
		this.currentElement = currentElement;
		this.currentNode = currentNode;
	}

	public Context getContext() {
		return context;
	}

	public ELEMENT getCurrentElement() {
		return currentElement;
	}

	public NODE getCurrentNode() {
		return currentNode;
	}

	public Object getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(Object currentObject) {
		this.currentObject = currentObject;
	}

	protected abstract void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor);
}
