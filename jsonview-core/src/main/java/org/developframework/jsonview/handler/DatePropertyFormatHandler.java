package org.developframework.jsonview.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyFormatHandler implements PropertyFormatHandler<String> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String handle(Object source) {
		if (source == null) {
			return null;
		}
		return sdf.format((Date) source);
	}

}
