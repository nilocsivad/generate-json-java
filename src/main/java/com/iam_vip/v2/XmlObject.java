/**
 * 
 */
package com.iam_vip.v2;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * @author Colin
 */
public abstract class XmlObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941173281869982829L;

	/**
	 * 
	 */
	public XmlObject() {
	}

	public abstract void parse(Element element);

	public abstract Object value();

}
