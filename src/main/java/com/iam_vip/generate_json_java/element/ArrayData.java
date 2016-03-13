/**
 * 
 */
package com.iam_vip.generate_json_java.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.dom4j.Element;


/**
 * @author Colin
 */
public class ArrayData implements IDataGenerate {
	
	/**
	 * 
	 */
	public ArrayData() {}
	
	/* (non-Javadoc)
	 * @see com.iam_vip.generate_json_java.element.IDataGenerate#put(java.util.Map, java.lang.String, org.dom4j.Element)
	 */
	@Override
	public void put( Map< String, Object > map, String key, Element element ) {
		
		int len = 10;
		String length = element.attributeValue( "length" );
		if ( null == length || "".equals( length ) ) {}
		else {
			if ( Pattern.matches( "\\d", length ) ) {
				int tmp = Integer.parseInt( length );
				len = tmp <= 0 ? len : tmp;
			}
		}
		
		List< Object > list = new ArrayList< >( 0 );
		
		String type = element.attributeValue( "type" );
		if ( null == type || "".equals( type ) ) {}
		else {
			switch ( type ) {
				case "integer":
					for ( int i = 0; len > i; ++ i )
						list.add( new Random().nextInt( 100 ) );
					break;
				case "double":
					for ( int i = 0; len > i; ++ i )
						list.add( new Random().nextDouble() );
					break;
				case "boolean":
					for ( int i = 0; len > i; ++ i )
						list.add( new Random().nextInt( 100 ) % 2 == 0 ? false : true );
					break;
				case "string":
				default:
					for ( int i = 0; len > i; ++ i ) {
						int l = new Random().nextInt( 30 ) + 20;
						StringBuffer buf = new StringBuffer( l );
						for ( int k = 0; k < l; ++ k ) {
							buf.append( StringData.ARRAY[ new Random().nextInt( StringData.LEN ) ] );
						}
						list.add( buf );
					}
					break;
			}
		}
		
		map.put( key, list.toArray() );
		
	}
	
}
