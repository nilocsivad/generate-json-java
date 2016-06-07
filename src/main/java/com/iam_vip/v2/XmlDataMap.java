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
public class XmlDataMap extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 645388194344909615L;
	
	public static final String TAG[] = { "object", "map", "dictionary" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlDataMap() {
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public void parse(Element element) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() {
		// TODO Auto-generated method stub
		return null;
	}

}
