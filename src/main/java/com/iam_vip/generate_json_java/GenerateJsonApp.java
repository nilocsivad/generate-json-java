package com.iam_vip.generate_json_java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;

/**
 * Hello world!
 */
public class GenerateJsonApp {

	public static final String STRING = "“哦……”林逸木讷的点了点头，明白了陈雨舒的意思。心道，自己还没吃饱呢，早知道多煮一点儿了！“你站在这里做什么？你站在这里我都吃不下东西了！”楚梦瑶没注意到林逸和陈雨舒之间的小动作，见他站在这里不走，顿时皱了皱眉。楚梦瑶还没有叫男人看着吃东西的习惯呢。  林逸转身出了餐厅，向自己的房间走去。这种大小姐犯不着和她生气，相比之下，虽然陈雨舒有些古灵精怪，但是却比楚梦瑶好说话多了。 林逸准备回去整理一下书包，虽然高中的课程他早已经自学完成了，但是好歹自己第一天上学，总要做做样子吧？“没了，想吃的话叫箭牌哥再做给你呗。”陈雨舒很是纳闷，怎么楚梦瑶光吃不胖呢？每次吃的都比自己多，却比自己要瘦上好几斤。";
	public static final String[] ARRAY = STRING.split( "" );
	public static final int LEN = ARRAY.length;

	public static void main( String[] args ) throws IOException, DocumentException {

		SAXReader saxReader = new SAXReader();

		File xmlFile = new File( "templete/generate-json-template.xml" );
		Document document = saxReader.read( xmlFile );

		Element root = document.getRootElement();

		String uname = root.attributeValue( "uname" );
		String key = root.attributeValue( "key" );

		System.out.println( String.format( "\"%s\" --- \"%s\"", uname, key ) );

		Map< String, Object > result = new HashMap< >();

		List< Element > list = root.elements();
		for ( Element element : list ) {
			//System.out.println( String.format( "%s --- %s", element.getName(), element.asXML() ) );
			putElement( element, result );
		}

		obj2json( result );

	}

	static void putElement( Element element, Map< String, Object > map ) {

		String name = element.getName();
		String key = element.attributeValue( "key" );

		if ( key == null || "".equals( key ) ) { return; }

		switch ( name ) {
		case "boolean":
			putBoolean( key, element, map );
			break;
		case "string":
			putString( key, element, map );
			break;
		case "integer":
			putInt( key, element, map );
			break;
		case "double":
			putDouble( key, element, map );
			break;
		case "array":
			putArray( key, element, map );
			break;
		}
	}

	static void putBoolean( String key, Element element, Map< String, Object > map ) {

		boolean num = false;

		String type = element.attributeValue( "type" );
		if ( null == type || "".equals( type ) || "default".equalsIgnoreCase( type ) || "true".equalsIgnoreCase( type ) || "false".equalsIgnoreCase( type ) ) {
		}
		else num = true;

		int i = new Random().nextInt() % 2;
		map.put( key, num ? i : i == 0 ? false : true );
	}

	static void putString( String key, Element element, Map< String, Object > map ) {

		int limit = 0;

		String maxLength = element.attributeValue( "max-length" );
		if ( null == maxLength || "".equals( maxLength ) ) {
		}
		else {
			if ( Pattern.matches( "\\d*", maxLength ) ) limit = Integer.parseInt( maxLength );
		}

		int l = new Random().nextInt() % 800;
		l = Math.abs( l );
		limit = limit == 0 ? l : l % limit;
		limit = limit <= 0 ? 20 : limit;

		//System.out.println( String.format( "max length is %d", limit ) );

		StringBuffer buffer = new StringBuffer( 16 );
		for ( int i = 0; i < limit; ++ i ) {
			int index = new Random().nextInt( LEN );
			buffer.append( ARRAY[ index ] );
		}

		map.put( key, buffer );
	}

	static void putInt( String key, Element element, Map< String, Object > map ) {

		boolean hasMax = false, hasMin = false, hasValues = false;
		int max = 0, min = 0;
		int[] values = null;

		// <max>
		String smax = element.attributeValue( "max" );
		if ( null == smax || "".equals( smax ) ) {
		}
		else {
			if ( Pattern.matches( "\\d*", smax ) ) {
				hasMax = true;
				max = Integer.parseInt( smax );
			}
		}
		// </max>

		// <min>
		String smin = element.attributeValue( "min" );
		if ( null == smin || "".equals( smin ) ) {
		}
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
			if ( null == svalues || "".equals( svalues ) ) {
			}
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

	static void putDouble( String key, Element element, Map< String, Object > map ) {
		double d = new Random().nextDouble() * new Random().nextInt( 10000 );
		map.put( key, d );
	}

	static void putArray( String key, Element element, Map< String, Object > map ) {

		int len = 10;
		String length = element.attributeValue( "length" );
		if ( null == length || "".equals( length ) ) {
		}
		else {
			if ( Pattern.matches( "\\d", length ) ) {
				int tmp = Integer.parseInt( length );
				len = tmp <= 0 ? len : tmp;
			}
		}

		List< Object > list = new ArrayList< >( 0 );

		String type = element.attributeValue( "type" );
		if ( null == type || "".equals( type ) ) {
		}
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
						buf.append( ARRAY[ new Random().nextInt( LEN ) ] );
					}
					list.add( buf );
				}
				break;
			}
		}

		map.put( key, list.toArray() );
	}

	static void obj2json( Object object ) {
		Gson gson = new Gson();
		String json = gson.toJson( object );
		System.out.println( json );
	}

}
