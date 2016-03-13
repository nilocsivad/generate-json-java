/**
 * 
 */
package com.iam_vip.generate_json_java.element;

import java.util.Map;
import java.util.Random;

import org.dom4j.Element;


/**
 * @author Colin
 */
public class BooleanData implements IDataGenerate {
	
	/**
	 * 
	 */
	public BooleanData() {}
	
	/* (non-Javadoc)
	 * @see com.iam_vip.generate_json_java.element.IDataGenerate#put(java.util.Map, java.lang.String, org.dom4j.Element)
	 */
	@Override
	public void put( Map< String, Object > map, String key, Element element ) {
		
		boolean num = false; // ** 是否产生数字结果 0/1
		
		String type = element.attributeValue( "type" ).toLowerCase();
		if ( null == type || "".equals( type ) || "default".equals( type ) || "true".equals( type ) || "false".equals( type ) ) {}
		else num = true;
		
		int i = new Random().nextInt() % 2;
		map.put( key, num ? i : ( i == 0 ? false : true ) );
		
	}
	
}
