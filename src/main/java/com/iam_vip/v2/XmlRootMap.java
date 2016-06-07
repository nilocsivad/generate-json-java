/**
 * 
 */
package com.iam_vip.v2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Colin
 */
public class XmlRootMap extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6988802755717133880L;
	
	public static final String TAG[] = { "object", "map", "dictionary" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlRootMap() {
	}

}
