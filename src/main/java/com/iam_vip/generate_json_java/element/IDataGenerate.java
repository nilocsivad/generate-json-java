/**
 * 
 */
package com.iam_vip.generate_json_java.element;

import java.util.Map;

import org.dom4j.Element;

/**
 * @author Colin
 */
public interface IDataGenerate {
	
	void put( Map< String, Object > map, String key, Element element );
	
}
