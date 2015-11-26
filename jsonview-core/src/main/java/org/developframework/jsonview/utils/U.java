package org.developframework.jsonview.utils;

import java.util.List;

public class U {

	public static final boolean isValid(String str) {
		return str != null && !str.isEmpty();
	}

	public static final String joint(Object[] array, String interval) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]).append(i == array.length - 1 ? "" : interval);
		}
		return sb.toString();
	}

	public static final String joint(List<?> list, String interval) {
		return joint(list.toArray(), interval);
	}

	public static final String camelCaseToUnderline(String camelCaseStr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, size = camelCaseStr.length(); i < size; i++) {
			char ch = camelCaseStr.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (i == 0) {
					sb.append((char) (ch + 32));
				} else {
					sb.append('_').append((char) (ch + 32));
				}
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
