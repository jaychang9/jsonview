package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ImportElementProcessor extends AbstractProcessor<ImportElement, ObjectNode> {

	public ImportElementProcessor(Context context, ImportElement currentElement, ObjectNode currentNode) {
		super(context, currentElement, currentNode);
	}

	@Override
	protected void process(AbstractProcessor<? extends AbstractElement, ? extends JsonNode> parentProcessor) {
		JsonviewConfiguration jsonviewConfiguration = super.context.getJsonviewConfiguration();
		Jsonview jsonview = jsonviewConfiguration.extractJsonview(super.currentElement.getNamespace(), super.currentElement.getId());
		JsonviewElementProcessor jsonviewElementProcessor = new JsonviewElementProcessor(super.context, jsonview, super.currentNode);
		jsonviewElementProcessor.setCurrentObject(parentProcessor.getCurrentObject());
		jsonviewElementProcessor.process(parentProcessor);
	}

}
