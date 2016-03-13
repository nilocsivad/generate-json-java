/**
 * 
 */
package com.iam_vip.generate_json_java.element;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.dom4j.Element;


/**
 * @author Colin
 */
public class IntData implements IDataGenerate {
	
	/**
	 * 
	 */
	public IntData() {}
	
	/* (non-Javadoc)
	 * @see com.iam_vip.generate_json_java.element.IDataGenerate#put(java.util.Map, java.lang.String, org.dom4j.Element)
	 */
	@Override
	public void put( Map< String, Object > map, String key, Element element ) {
		
		boolean hasMax = false, hasMin = false, hasValues = false;
		int max = 0, min = 0;
		int[] values = null;
		
		// <max>
		String smax = element.attributeValue( "max" );
		if ( null == smax || "".equals( smax ) ) {}
		else {
			if ( Pattern.matches( "\\d*", smax ) ) {
				hasMax = true;
				max = Integer.parseInt( smax );
			}
		}
		// </max>
		
		// <min>
		String smin = element.attributeValue( "min" );
		if ( null == smin || "".equals( smin ) ) {}
		else {
			if ( Pattern.matches( "\\d*", smin ) ) {
				hasMin = true;
				min = Integer.parseInt( smin );
			}
		}
		// </min>
		
		// <no-max-or-min-attribute>
		if ( !( hasMax || hasMin ) ) {
			String svalues = element.attributeValue( "values" );
			if ( null == svalues || "".equals( svalues ) ) {}
			else {
				if ( Pattern.matches( "(\\d)|([\\d,]*\\d)", svalues ) ) {
					
					hasValues = true;
					
					String[] arr = svalues.split( "," );
					values = new int[ arr.length ];
					
					for ( int i = 0, l = arr.length; l > i; ++ i )
						values[ i ] = Integer.parseInt( arr[ i ] );
				}
			}
		}
		// </no-max-or-min-attribute>
		
		int num = 0;
		
		if ( hasMax && hasMin ) {
			num = new Random().nextInt( max - min ) + min;
		}
		else if ( hasMax ) {
			num = new Random().nextInt( max );
		}
		else if ( hasMin ) {
			num = min + Math.abs( new Random().nextInt() );
		}
		else if ( hasValues ) {
			num = values[ new Random().nextInt( values.length ) ];
		}
		else {
			num = new Random().nextInt();
		}
		
		map.put( key, num );
		
	}
	
}
