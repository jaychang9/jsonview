package org.developframework.jsonview.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DataModel extends HashMap<String, Object> {

	private static final long serialVersionUID = 5735893765994945206L;

	public Object extractData(Object parent, String with, String data) {

		String[] propertyNames = with.split("\\.");
		propertyNames[0] = parent == null ? "$root" : propertyNames[0];
		parent = parent == null ? this : (propertyNames[0].equals("$root") ? this : parent);
		for (int i = 1; i < propertyNames.length; i++) {
			parent = value(parent, propertyNames[i]);
		}
		return value(parent, data);
	}

	private Object value(Object parent, String propertyName) {
		if (parent == null) {
			return null;
		} else if (parent instanceof Map) {
			return ((Map<?, ?>) parent).get(propertyName);
		} else {
			Field field;
			try {
				field = parent.getClass().getDeclaredField(propertyName);
				field.setAccessible(true);
				return field.get(parent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
