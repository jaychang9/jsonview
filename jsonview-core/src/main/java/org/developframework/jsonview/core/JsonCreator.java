package org.developframework.jsonview.core;

import java.io.IOException;

import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.JsonviewElementProcessor;
import org.developframework.jsonview.exception.JsonviewNotFoundException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonCreator {

	private JsonviewConfiguration jsonviewConfiguration;
	private ObjectMapper objectMapper;

	public JsonCreator(JsonviewConfiguration jsonviewConfiguration, ObjectMapper objectMapper) {
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = objectMapper;
	}

	private ObjectNode constructJson(DataModel dataModel, String namespace, String id) {
		Jsonview jsonview = this.jsonviewConfiguration.extractJsonview(namespace, id);
		if (jsonview == null) {
			throw new JsonviewNotFoundException(String.format("Jsonview \"%s\" is not Found in namespace \"%s\".", id, namespace));
		}
		ObjectNode root = objectMapper.createObjectNode();
		Context context = new Context();
		context.setDataModel(dataModel);
		context.setObjectMapper(objectMapper);
		context.setJsonviewConfiguration(jsonviewConfiguration);
		JsonviewElementProcessor processor = new JsonviewElementProcessor(context, jsonview, root);
		processor.process(null);
		return root;
	}

	public String createJson(DataModel dataModel, String namespace, String id) {
		ObjectNode root = constructJson(dataModel, namespace, id);
		try {
			return objectMapper.writeValueAsString(root);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id) {
		ObjectNode root = constructJson(dataModel, namespace, id);
		try {
			objectMapper.writeValue(generator, root);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
