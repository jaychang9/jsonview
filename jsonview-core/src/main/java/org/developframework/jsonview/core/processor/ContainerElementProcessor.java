package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ContainerElement;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ContainerElementProcessor<ELEMENT extends ContainerElement, NODE extends JsonNode> extends AbstractProcessor<ELEMENT, NODE> {

	public ContainerElementProcessor(Context context, ELEMENT currentElement, NODE currentNode) {
		super(context, currentElement, currentNode);
	}
}
