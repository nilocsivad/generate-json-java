package com.iam_vip.generate_json_java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.iam_vip.generate_json_java.element.ArrayData;
import com.iam_vip.generate_json_java.element.BooleanData;
import com.iam_vip.generate_json_java.element.DoubleData;
import com.iam_vip.generate_json_java.element.IntData;
import com.iam_vip.generate_json_java.element.StringData;

/**
 * Hello world!
 */
public class GenerateJson {
	
	
	/**
	 */
	public GenerateJson() {
		super();
	}
	
	
	/**
	 * @param xml
	 */
	public GenerateJson( String xml ) {
		super();
		this.xml = xml;
	}
	
	
	String xml = null;
	
	
	@Before
	public void init() {
		
		xml = "templete/generate-json-template.xml";
	}
	
	
	@Test
	public void main() throws IOException, DocumentException {
		
		this.generate();
	}
	
	public Map< String, Object > generate() throws IOException, DocumentException {
		
		System.out.println( System.currentTimeMillis() );
		
		SAXReader saxReader = new SAXReader();
		
		File xmlFile = new File( xml );
		Document document = saxReader.read( xmlFile );
		
		Element root = document.getRootElement();
		
		// String uname = root.attributeValue( "uname" );
		// String key = root.attributeValue( "key" );
		// System.out.println( String.format( "\"%s\" --- \"%s\"", uname, key ) );
		
		Map< String, Object > result = new HashMap< >( 8 );
		List< Element > elements = root.elements();
		for ( Element ele : elements )
			this.putElement( ele, result );
		// obj2json( result );
		
		System.out.println( System.currentTimeMillis() );
		return result;
	}
	
	void putElement( Element element, Map< String, Object > map ) {
		
		String name = element.getName().toLowerCase();
		String key = element.attributeValue( "key" );
		
		if ( key == null || "".equals( key ) ) { return; }
		
		switch ( name ) {
			case "boolean":
				new BooleanData().put( map, key, element );
				break;
			case "string":
				new StringData().put( map, key, element );
				break;
			case "integer":
				new IntData().put( map, key, element );
				break;
			case "double":
				new DoubleData().put( map, key, element );
				break;
			case "array":
				new ArrayData().put( map, key, element );
				break;
			case "object":
				
				
				Map< String, Object > obj_result = new HashMap< >( 8 );
				List< Element > obj_elements = element.elements();
				for ( Element ele : obj_elements ) {
					this.putElement( ele, obj_result );
				}
				map.put( key, obj_result );
				
			case "list":
				
				
				// <length>
				int length = 0;
				String slength = element.attributeValue( "length" );
				if ( null == slength || "".equals( slength ) ) {}
				else {
					if ( Pattern.matches( "\\d*", slength ) ) {
						length = Integer.parseInt( slength );
					}
				}
				// </length>
				
				if ( length > 0 ) {
					List< Map< String, Object > > li_result = new ArrayList< >( 8 );
					List< Element > li_elements = element.elements();
					
					for ( int i = 0; i < length; ++ i ) {
						Map< String, Object > li_map = new HashMap< >( 8 );
						
						for ( Element ele : li_elements ) {
							this.putElement( ele, li_map );
						}
						li_result.add( li_map );
					}
					map.put( key, li_result );
				}
				
				break;
				
				
		}
	}
	
	void obj2json( Object object ) throws IOException {
		
		Gson gson = new Gson();
		String json = gson.toJson( object ).trim();
		System.out.println( json );
		
		FileWriter fw = new FileWriter( String.format( "E:\\tmp\\gj-%d.txt", /*System.currentTimeMillis()*/1 ) );
		fw.write( "\r\n" );
		fw.write( json );
		fw.write( "\r\n" );
		fw.close();
	}
	
	
	
	/**
	 * @param xml the xml to set
	 */
	public void setXml( String xml ) {
		
		this.xml = xml;
	}
	
}
