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
public class XmlDataList extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301560450684035658L;
	
	public static final String TAG[] = { "array", "list", "set", "collection" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlDataList() {
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
