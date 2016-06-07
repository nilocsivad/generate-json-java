/**
 * 
 */
package com.iam_vip.v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;

/**
 * @author Colin
 */
public class XmlParser {

	public static final Map<List<String>, Class<?>> ROOT = new HashMap<List<String>, Class<?>>(3);
	public static final Map<List<String>, Class<?>> DATA = new HashMap<List<String>, Class<?>>(7);

	static {

		ROOT.put(XmlRootList.TAGS, XmlRootList.class);
		ROOT.put(XmlRootMap.TAGS, XmlRootMap.class);

		DATA.put(XmlDataList.TAGS, XmlDataList.class);
		DATA.put(XmlDataMap.TAGS, XmlDataMap.class);
		DATA.put(XmlDataNumber.TAGS, XmlDataNumber.class);
		DATA.put(XmlDataString.TAGS, XmlDataString.class);
		DATA.put(XmlDataBoolean.TAGS, XmlDataBoolean.class);
	}

	/**
	 * 
	 */
	public XmlParser() {
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(System.currentTimeMillis());



		String xml = "templete/template-base.xml";
		File xmlFile = new File(xml);

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlFile);

		Element root = document.getRootElement();
		String rootName = root.getName();

		Object result = null;
		if (XmlRootList.TAGS.contains(rootName)) {
			result = parse2XmlList(root);
		}
		else if (XmlRootMap.TAGS.contains(rootName)) {
			result = parse2XmlMap(root);
		}

		if (result == null) {
			System.out.println("null");
		}

		obj2json(result);



		System.out.println(System.currentTimeMillis());
	}

	public static void obj2json(Object object) throws IOException {

		Gson gson = new Gson();
		String json = gson.toJson(object).trim();
		System.out.println(json);

		FileWriter fw = new FileWriter(String.format("E:\\tmp\\gj-%d.txt", System.currentTimeMillis()));
		fw.write("\r\n");
		fw.write(json);
		fw.write("\r\n");
		fw.close();
	}

	public static Object parse2XmlList(Element root) throws DocumentException, InstantiationException, IllegalAccessException {
		XmlRootList list = new XmlRootList();

		List<Element> elements = root.elements();
		int size = Integer.parseInt(root.attributeValue("size"));

		for (int i = 0; i < size; ++i) {

			// 数据项
			Map<String, Object> item = new HashMap<String, Object>(elements.size() + 1);

			for (Element element : elements) {

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

		return list;
	}

	public static Object parse2XmlMap(Element root) throws DocumentException, InstantiationException, IllegalAccessException {
		XmlRootMap map = new XmlRootMap();

		List<Element> elements = root.elements();
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
