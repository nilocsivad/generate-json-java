/**
 * 
 */
package com.iam_vip.v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;

/**
 * @author Colin
 */
public class __XmlParser implements __IXml {

	static {

		ROOT.put(XmlList.TAGS, XmlList.class);
		ROOT.put(XmlMap.TAGS, XmlMap.class);

		DATA.put(XmlList.TAGS, XmlList.class);
		DATA.put(XmlMap.TAGS, XmlMap.class);
		DATA.put(XmlNumber.TAGS, XmlNumber.class);
		DATA.put(XmlString.TAGS, XmlString.class);
		DATA.put(XmlBoolean.TAGS, XmlBoolean.class);
	}

	/**
	 * 
	 */
	public __XmlParser() {
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(System.currentTimeMillis());



		String xml = "templete/template-full.xml";
		File xmlFile = new File(xml);

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlFile);

		Element root = document.getRootElement();
		String rootName = root.getName();

		Object result = null;
		if (XmlList.TAGS.contains(rootName)) {
			result = new XmlList().parse(root).value();
		}
		else if (XmlMap.TAGS.contains(rootName)) {
			result = new XmlMap().parse(root).value();
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

}
