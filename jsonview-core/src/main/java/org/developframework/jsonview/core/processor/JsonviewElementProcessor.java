package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.Jsonview;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonviewElementProcessor extends ObjectElementProcessor {

	public JsonviewElementProcessor(Context context, Jsonview jsonview, ObjectNode currentNode) {
		super(context, jsonview, currentNode);
	}

	@Override
	public void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		for (Iterator<AbstractElement> iterator = super.currentElement.elementIterator(); iterator.hasNext();) {
			AbstractElement abstractElement = iterator.next();
			super.executeNextProcessor(abstractElement, abstractElement);
		}
	}

}
