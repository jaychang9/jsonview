package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;
import java.util.Collection;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.ArrayElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArrayElementProcessor extends ContainerElementProcessor<ArrayElement, ArrayNode> {

	public ArrayElementProcessor(Context context, ArrayElement currentElement, ArrayNode currentNode) {
		super(context, currentElement, currentNode);
	}

	@Override
	protected void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		super.currentObject = super.context.getDataModel().extractData(parentProcessor.getCurrentObject(), super.currentElement.getWith(), super.currentElement.getData());
		if (super.currentObject == null)
			return;
		if (super.currentObject.getClass().isArray()) {
			array(parentProcessor);
		} else if (super.currentObject instanceof Collection<?>) {
			collection();
		}
	}

	private void array(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		Object[] objs = (Object[]) super.currentObject;
		for (Object object : objs) {
			sinple(object);
		}
	}

	private void collection() {
		Collection<?> collection = (Collection<?>) super.currentObject;
		for (Object object : collection) {
			sinple(object);
		}
	}

	private void sinple(Object object) {
		if (currentElement.isElementEmpty()) {
			empty(object);
		} else {
			ObjectNode objectNode = super.context.getObjectMapper().createObjectNode();
			ObjectElementProcessor childProcessor = new ObjectElementProcessor(context, currentElement.getChildElement(), objectNode);
			childProcessor.setCurrentObject(object);
			childProcessor.process(null);
			currentNode.add(objectNode);
		}
	}

	private void empty(Object object) {
		if (object == null) {
			currentNode.addNull();
		} else if (object instanceof Integer) {
			currentNode.add((Integer) object);
		} else if (object instanceof Long) {
			currentNode.add((Long) object);
		} else if (object instanceof Boolean) {
			currentNode.add((Boolean) object);
		} else if (object instanceof Float) {
			currentNode.add((Float) object);
		} else if (object instanceof Double) {
			currentNode.add((Double) object);
		} else if (object instanceof BigDecimal) {
			currentNode.add((BigDecimal) object);
		} else {
			currentNode.add(object.toString());
		}
	}

}
