/**
 * 
 */
package com.iam_vip.v2.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.dom4j.Element;

/**
 * @author Colin
 */
public class XmlNumber extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6471161926537712053L;

	public static final int MAX = 1000000;

	public static final String TAG[] = { "int", "integer", "Integer", "double", "float", "number", "decimal" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlNumber() {
	}

	private int min = 0, max = MAX;
	private int precision = 0; // 精度 即小数点后的位数
	private List<Integer> values = new ArrayList<Integer>();

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public XmlNumber parse(Element element) {

		String min_attr = element.attributeValue("min");
		if (min_attr != null && !"".equals(min_attr)) {
			this.min = Integer.parseInt(min_attr);
		}

		String max_attr = element.attributeValue("max");
		if (max_attr != null && !"".equals(max_attr)) {
			this.max = Integer.parseInt(max_attr);
			if (this.max > MAX)
				this.max = MAX;
		}

		String precision_attr = element.attributeValue("precision");
		if (precision_attr != null && !"".equals(precision_attr)) {
			this.precision = Integer.parseInt(precision_attr);
		}

		String values_attr = element.attributeValue("values");
		if (values_attr != null && Pattern.matches("(\\d)|([\\d,]*\\d)", values_attr)) {
			String[] values_arr = values_attr.split(",");
			for (String val : values_arr) {
				this.values.add(Integer.parseInt(val));
			}
		}

		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() throws Exception {

		Object result = 0;

		if (this.values == null || this.values.size() == 0) {

			int num = this.max - this.min;
			result = this.min + new Random().nextInt(num);

			if (this.precision > 0) {
				String suffix = Math.random() + "";
				result += "." + (suffix.substring(3, 3 + this.precision));
			}
			// generate number
		}
		else { // given values
			int index = new Random().nextInt(this.values.size());
			result = this.values.get(index);
		}

		return result;
	}

}
