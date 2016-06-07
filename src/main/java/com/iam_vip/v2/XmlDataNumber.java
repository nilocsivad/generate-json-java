/**
 * 
 */
package com.iam_vip.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.dom4j.Element;

/**
 * @author Colin
 */
public class XmlDataNumber extends XmlObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6471161926537712053L;

	public static final String TAG[] = { "int", "integer", "Integer", "double", "float", "number", "decimal" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlDataNumber() {
	}

	private long min = Long.MIN_VALUE, max = Long.MAX_VALUE;
	private int precision = 0; // 精度 即小数点后的位数
	private List<Long> values = new ArrayList<Long>();

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#parse(org.dom4j.Element)
	 */
	@Override
	public void parse(Element element) {

		String min_attr = element.attributeValue("min");
		if (min_attr != null && !"".equals(min_attr)) {
			this.min = Long.parseLong(min_attr);
		}

		String max_attr = element.attributeValue("max");
		if (max_attr != null && !"".equals(max_attr)) {
			this.max = Long.parseLong(max_attr);
		}

		String precision_attr = element.attributeValue("precision");
		if (precision_attr != null && !"".equals(precision_attr)) {
			this.precision = Integer.parseInt(precision_attr);
		}

		String values_attr = element.attributeValue("values");
		if (values_attr != null && Pattern.matches("(\\d)|([\\d,]*\\d)", values_attr)) {
			String[] values_arr = values_attr.split(",");
			for (String val : values_arr) {
				this.values.add(Long.parseLong(val));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.v2.XmlObject#value()
	 */
	@Override
	public Object value() {

		Object result = 0;

		if (this.values == null || this.values.size() == 0) {

			if (this.min == Long.MIN_VALUE && this.max == Long.MAX_VALUE) {
				result = new Random().nextInt();
			}
			else if ((this.min > Long.MIN_VALUE && this.max <= Long.MAX_VALUE) || (this.min >= Long.MIN_VALUE && this.max < Long.MAX_VALUE)) {
				long num = this.max - this.min;

				if (num <= Integer.MAX_VALUE) {
					result = this.min + new Random().nextInt((int) num);
				}
				else {
					long con = new Random().nextLong();
					while (con > num) {
						con = new Random().nextLong();
					}
					result = this.min + con;
				}

			}
			else {
				result = System.currentTimeMillis();
			}

			if (this.precision > 0) {
				String suffix = new Random().nextInt() + "";
				result += "." + (suffix.substring(0, this.precision));
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
