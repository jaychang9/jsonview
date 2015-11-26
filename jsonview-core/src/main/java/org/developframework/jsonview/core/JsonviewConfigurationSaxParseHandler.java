package org.developframework.jsonview.core;

import java.util.Stack;

import org.developframework.jsonview.core.element.AbstractElement;
import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.exception.ResourceNotUniqueException;
import org.developframework.jsonview.utils.U;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JsonviewConfigurationSaxParseHandler extends DefaultHandler {

	private JsonviewConfiguration jsonviewConfiguration;
	private JsonviewPackage jsonviewPackage;
	private Stack<AbstractElement> stack;

	public JsonviewConfigurationSaxParseHandler(JsonviewConfiguration jsonviewConfiguration) {
		this.jsonviewConfiguration = jsonviewConfiguration;
	}

	@Override
	public void startDocument() throws SAXException {
		stack = new Stack<AbstractElement>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
			case "property" : {
				PropertyElement propertyElement = new PropertyElement();
				propertyElement.setData(attributes.getValue("data"));
				propertyElement.setUnderlineData(U.camelCaseToUnderline(attributes.getValue("data")));
				propertyElement.setWith(attributes.getValue("with"));
				propertyElement.setAlias(attributes.getValue("alias"));
				propertyElement.setHandler(attributes.getValue("handler"));
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(propertyElement);
				}
			}
			break;
			case "object" : {
				ObjectElement objectElement = new ObjectElement();
				objectElement.setData(attributes.getValue("data"));
				objectElement.setUnderlineData(U.camelCaseToUnderline(attributes.getValue("data")));
				objectElement.setWith(attributes.getValue("with"));
				objectElement.setAlias(attributes.getValue("alias"));
				objectElement.setType(attributes.getValue("type"));
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(objectElement);
				}
				stack.push(objectElement);
			}
			break;
			case "array" : {
				ArrayElement arrayElement = new ArrayElement();
				arrayElement.setData(attributes.getValue("data"));
				arrayElement.setUnderlineData(U.camelCaseToUnderline(attributes.getValue("data")));
				arrayElement.setWith(attributes.getValue("with"));
				arrayElement.setAlias(attributes.getValue("alias"));
				arrayElement.setType(attributes.getValue("type"));
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(arrayElement);
				}
				stack.push(arrayElement);
			}
			break;
			case "jsonview" : {
				Jsonview jsonview = new Jsonview();
				jsonview.setId(attributes.getValue("id").trim());
				String extend = attributes.getValue("extend");
				if (U.isValid(extend)) {
					jsonview.setExtend(extend.trim());
				}
				stack.push(jsonview);
			}
			break;
			case "import" : {
				String namespace = attributes.getValue("namespace");
				String id = attributes.getValue("id");

				namespace = U.isValid(namespace) ? namespace.trim() : jsonviewPackage.getNamespace();
				ImportElement importElement = new ImportElement(namespace, id);
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(importElement);
				}
			}
			break;
			case "jsonview-package" : {
				jsonviewPackage = new JsonviewPackage();
				jsonviewPackage.setNamespace(attributes.getValue("namespace").trim());
			}
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
			case "object" : {
				stack.pop();
			}
			break;
			case "array" : {
				((ArrayElement) stack.pop()).createChildElement();
			}
			break;
			case "jsonview" : {
				Jsonview jsonview = (Jsonview) stack.pop();
				if (jsonviewPackage.containsKey(jsonview.getId())) {
					throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" is exist.", jsonview.getId()));
				}
				jsonviewPackage.push(jsonview);
			}
			break;
			case "jsonview-package" : {
				if (jsonviewConfiguration.containsKey(jsonviewPackage.getNamespace())) {
					throw new ResourceNotUniqueException(String.format("JsonviewPackage namespace \"%s\" is exist.", jsonviewPackage.getNamespace()));
				}
				jsonviewConfiguration.push(jsonviewPackage);
			}
			break;
		}
	}
}
