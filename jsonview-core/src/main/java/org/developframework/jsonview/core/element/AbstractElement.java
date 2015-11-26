package org.developframework.jsonview.core.element;

import org.developframework.jsonview.utils.U;

public abstract class AbstractElement {

	protected String data;
	protected String underlineData;
	protected String with;
	protected String alias;

	public String showName(boolean isUnderlineMode) {
		return U.isValid(alias) ? alias : (isUnderlineMode ? underlineData : data);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUnderlineData() {
		return underlineData;
	}

	public void setUnderlineData(String underlineData) {
		this.underlineData = underlineData;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getWith() {
		return with;
	}

	public void setWith(String with) {
		if (U.isValid(with)) {
			if (!with.startsWith("$")) {
				with = "$parent." + with.trim();
			} else {
				with = with.trim();
			}
		} else {
			with = "$parent";
		}
		this.with = with;
	}

}
