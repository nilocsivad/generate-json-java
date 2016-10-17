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
import com.iam_vip.v2.type.XmlBoolean;
import com.iam_vip.v2.type.XmlList;
import com.iam_vip.v2.type.XmlMap;
import com.iam_vip.v2.type.XmlNumber;
import com.iam_vip.v2.type.XmlString;

/**
 * @author Colin
 */
public class XmlParser implements __IXml {

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
	public XmlParser() {
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(System.currentTimeMillis());



		String xml = "templete/template-full-list.xml";
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

		
		toJson(result);
		System.out.println(result);


		System.out.println(System.currentTimeMillis());
	}

	public static void toJson(Object object) throws IOException {

		Gson gson = new Gson();
		String json = gson.toJson(object).trim();
		System.out.println(json);

		FileWriter fw = new FileWriter(String.format("E:\\tmp\\json-%d.txt", System.currentTimeMillis()));
		fw.write("\r\n");
		fw.write(json);
		fw.write("\r\n");
		fw.close();
	}

}
