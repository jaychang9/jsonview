package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectElementProcessor extends ContainerElementProcessor<ObjectElement, ObjectNode> {

	public ObjectElementProcessor(Context context, ObjectElement currentElement, ObjectNode currentNode) {
		super(context, currentElement, currentNode);
	}

	@Override
	protected void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		if (parentProcessor != null) {
			super.currentObject = super.context.getDataModel().extractData(parentProcessor.getCurrentObject(), super.currentElement.getWith(), super.currentElement.getData());
		}
		for (Iterator<AbstractElement> iterator = super.currentElement.elementIterator(); iterator.hasNext();) {
			AbstractElement abstractElement = iterator.next();
			this.executeNextProcessor(currentElement, abstractElement);
		}
	}

	protected void executeNextProcessor(AbstractElement element, AbstractElement nextElement) {
		AbstractProcessor<? extends AbstractElement, ? extends JsonNode> nextProcessor = null;
		if (nextElement instanceof PropertyElement) {
			nextProcessor = new PropertyElementProcessor(context, (PropertyElement) nextElement);
		} else if (nextElement instanceof ObjectElement) {
			if (((ObjectElement) nextElement).isEntity()) {
				Object obj = super.context.getDataModel().extractData(currentObject, nextElement.getWith(), nextElement.getData());
				if (obj == null) {
					currentNode.putNull(element.showName(super.context.isUnderlineMode()));
					return;
				}
			}
			// 新建对象节点
			ObjectNode objectNode = currentNode.putObject(element.showName(super.context.isUnderlineMode()));
			nextProcessor = new ObjectElementProcessor(context, (ObjectElement) nextElement, objectNode);
		} else if (nextElement instanceof ArrayElement) {
			if (((ArrayElement) nextElement).isEntity()) {
				Object obj = super.context.getDataModel().extractData(currentObject, nextElement.getWith(), nextElement.getData());
				if (obj == null) {
					currentNode.putNull(element.showName(super.context.isUnderlineMode()));
					return;
				}
			}
			// 新建数组节点
			ArrayNode arrayNode = currentNode.putArray(element.showName(super.context.isUnderlineMode()));
			nextProcessor = new ArrayElementProcessor(context, (ArrayElement) nextElement, arrayNode);
		} else if (nextElement instanceof ImportElement) {
			nextProcessor = new ImportElementProcessor(context, (ImportElement) nextElement, super.currentNode);
		}
		// 处理下一层节点
		nextProcessor.process(this);
	}
}
