/**
 * 
 */
package com.iam_vip.v2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * @author Colin
 */
public class XmlMap extends XmlObject implements __IXml {

	/**
	 * 
	 */
	private static final long serialVersionUID = 645388194344909615L;

	public static final String TAG[] = { "object", "map", "dictionary" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlMap() {
	}

	private List<Element> elements;

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public XmlMap parse(Element element) {
		this.elements = element.elements();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() throws Exception {

		// 数据项
		Map<String, Object> map = new HashMap<String, Object>(this.elements.size() + 1);

		for (Element element : elements) {

			String elementName = element.getName();
			String elementKey = element.attributeValue("key");

			XmlObject data = null;
			for (List<String> key : DATA.keySet()) {
				if (key.contains(elementName)) {
					data = (XmlObject) DATA.get(key).newInstance();
					data.parse(element);
					// System.out.println(data.getClass().getName());
					map.put(elementKey, data.value());
					break;
				}
			}
		}

		return map;
	}

}
