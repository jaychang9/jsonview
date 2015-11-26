package org.developframework.jsonview.core;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.exception.ConfigurationFileNotFoundException;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonviewConfigurationSaxReader {

	private static final Logger log = LoggerFactory.getLogger("org.developframework.jsonview");
	private String[] configs;

	public JsonviewConfigurationSaxReader(String[] configs) {
		this.configs = configs;
	}

	public JsonviewConfiguration readConfiguration() {
		JsonviewConfiguration jsonviewConfiguration = new JsonviewConfiguration();
		for (String config : configs) {
			InputStream is = this.getClass().getResourceAsStream(config);
			if (is == null) {
				throw new ConfigurationFileNotFoundException("Jsonview framework load the configuration is failed, because not found: " + config);
			} else {
				log.info(String.format("Jsonview framework load the configuration file \"%s\" is success.", config));
			}
			try {
				readOneFile(jsonviewConfiguration, is);
				is.close();
			} catch (Exception e) {
				throw new JsonviewParseXmlException("Jsonview framework parse XML Error for configuration file: " + config, e);
			}
		}
		return jsonviewConfiguration;
	}

	private void readOneFile(JsonviewConfiguration jsonviewConfiguration, InputStream is) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		JsonviewConfigurationSaxParseHandler handler = new JsonviewConfigurationSaxParseHandler(jsonviewConfiguration);
		saxParser.parse(is, handler);
		is.close();
	}

}
