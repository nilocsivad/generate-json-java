/**
 * 
 */
package com.iam_vip.v2;

import java.util.Arrays;
import java.util.List;

import org.dom4j.Element;

/**
 * @author Colin
 */
public class XmlDataBoolean extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7031142073882844460L;

	public static final String TAG[] = { "boolean", "bool" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	private static final String TYPE[] = { "", "default", "number" };
	private static final List<String> TYPES = Arrays.asList(TYPE);

	/**
	 * 
	 */
	public XmlDataBoolean() {
	}

	private String type;

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public void parse(Element element) {
		String val = element.attributeValue("type");
		this.type = (TYPES.contains(val)) ? val : "default";
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() {

		long r2 = System.currentTimeMillis() % 2;

		Object result = false;

		switch (this.type) {
		case "number":
			result = r2;
			break;
		case "default":
		default:
			if (r2 == 1)
				result = true;
			break;
		}
		return result;
	}

}
