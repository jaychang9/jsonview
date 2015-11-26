package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.handler.PropertyFormatHandler;
import org.developframework.jsonview.utils.U;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PropertyElementProcessor extends AbstractProcessor<PropertyElement, JsonNode> {

	public PropertyElementProcessor(Context context, PropertyElement currentElement) {
		super(context, currentElement, null);
	}

	@Override
	protected void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		super.currentObject = super.context.getDataModel().extractData(parentProcessor.getCurrentObject(), super.currentElement.getWith(), super.currentElement.getData());
		// handler¥¶¿Ì
		super.currentObject = invokeHandler(super.getCurrentElement().getHandler(), super.currentObject);
		ObjectNode parent = (ObjectNode) parentProcessor.getCurrentNode();
		if (currentObject == null) {
			parent.putNull(super.currentElement.showName(super.context.isUnderlineMode()));
		} else if (currentObject instanceof Integer) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (Integer) currentObject);
		} else if (currentObject instanceof Long) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (Long) currentObject);
		} else if (currentObject instanceof Boolean) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (Boolean) currentObject);
		} else if (currentObject instanceof Float) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (Float) currentObject);
		} else if (currentObject instanceof Double) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (Double) currentObject);
		} else if (currentObject instanceof BigDecimal) {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), (BigDecimal) currentObject);
		} else {
			parent.put(super.currentElement.showName(super.context.isUnderlineMode()), currentObject.toString());
		}
	}

	private Object invokeHandler(String handlerName, Object source) {
		if (U.isValid(handlerName)) {
			try {
				Class<?> clazz = Class.forName(handlerName);
				Object handler = clazz.newInstance();
				if (handler instanceof PropertyFormatHandler<?>) {
					return ((PropertyFormatHandler<?>) handler).handle(source);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return source;
	}
}
