/**
 * 
 */
package com.iam_vip.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Colin
 */
public class XmlRootList extends ArrayList<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 171968817072244791L;
	
	public static final String TAG[] = { "array", "list", "set", "collection" };
	public static final List<String> TAGS = Arrays.asList(TAG);

	/**
	 * 
	 */
	public XmlRootList() {
	}

}
