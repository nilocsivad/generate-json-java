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
public class DoubleData implements IDataGenerate {
	
	/**
	 * 
	 */
	public DoubleData() {}
	
	/* (non-Javadoc)
	 * @see com.iam_vip.generate_json_java.element.IDataGenerate#put(java.util.Map, java.lang.String, org.dom4j.Element)
	 */
	@Override
	public void put( Map< String, Object > map, String key, Element element ) {
		
		double d = new Random().nextDouble() * new Random().nextInt( 10000 );
		map.put( key, d );
		
	}
	
}
