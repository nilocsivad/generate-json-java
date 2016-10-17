/**
 * 
 */
package com.iam_vip.v2.type;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.dom4j.Element;

/**
 * @author Colin
 */
public class XmlString extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4140699512227045356L;

	public static final String TAG[] = { "String", "string" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	public static final String VALUE[] = { "你好", "不要", "价格", "近千元", "酒店", "房间", "休息", "发挥", "家长", "属性", "高考", "经济", "用户", "转换", "类型", "定义", "函数", "百分比 ", "元素", "货币", "依赖", "数字" };

	/**
	 * 
	 */
	public XmlString() {
	}

	private int maxLength = Integer.MAX_VALUE;

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public XmlString parse(Element element) {

		String max_attr = element.attributeValue("max-length");
		if (max_attr != null && !"".equals(max_attr)) {
			this.maxLength = Integer.parseInt(max_attr);
		}

		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() throws Exception {

		int len = this.maxLength == Integer.MAX_VALUE ? 500 : this.maxLength;
		int toLen = new Random().nextInt(len);

		int al = VALUE.length;

		StringBuffer buf = new StringBuffer(toLen);
		while (buf.length() < toLen) {
			int index = new Random().nextInt(al);
			buf.append(VALUE[index]);
		}

		return buf;
	}

}
