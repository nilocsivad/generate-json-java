/**
 * 
 */
package com.iam_vip.v2.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.iam_vip.v2.__IXml;

/**
 * @author Colin
 */
public class XmlList extends XmlObject implements __IXml {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301560450684035658L;

	public static final String TAG[] = { "array", "list", "set", "collection" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlList() {
	}

	private List<Element> elements;
	private int size = LENGTH;

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public XmlList parse(Element element) {

		this.elements = element.elements();

		String size_attr = element.attributeValue("size");
		if (!(size_attr == null || "".equals(size_attr))) {
			this.size = Integer.parseInt(size_attr);
		}

		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() throws Exception {

		List<Object> list = new ArrayList<Object>(this.size + 1);

		if (this.elements.size() == 1) { // List with value

			Element element = this.elements.get(0);

			String elementName = element.getName();

			for (int i = 0; i < this.size; ++i) {

				XmlObject data = null;

				for (List<String> key : DATA.keySet()) {
					if (key.contains(elementName)) {
						data = (XmlObject) DATA.get(key).newInstance();
						data.parse(element);
						break;
					}
				}

				if (data != null) {
					list.add(data.value());
				}
			}

			// List with value
		}
		else {

			for (int i = 0; i < this.size; ++i) {

				// 数据项
				Map<String, Object> item = new HashMap<String, Object>(this.elements.size() + 1);

				for (Element element : this.elements) {

					String elementName = element.getName();
					String elementKey = element.attributeValue("key");
					XmlObject data = null;

					for (List<String> key : DATA.keySet()) {
						if (key.contains(elementName)) {
							data = (XmlObject) DATA.get(key).newInstance();
							data.parse(element);
							break;
						}
					}

					if (data != null) {
						item.put(elementKey, data.value());
					}

				} // for (Element element : elements) {}

				list.add(item);

			} // Root Element List Size

		} // List with object

		return list;
	}

}
