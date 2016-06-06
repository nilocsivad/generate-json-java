package com.iam_vip.generate_json_java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Unit test for simple App.
 */
public class AppTest {


	@Test
	public void run() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("E:\\tmp\\json.txt"));
		reader.readLine();
		String json = reader.readLine();
		reader.close();

		System.out.println("Json: " + json);

		JsonElement je = new JsonParser().parse(json);
		boolean jnull = je.isJsonNull(), jobj = je.isJsonObject(), jarr = je.isJsonArray();

		System.out.println(jnull + " -- " + jobj + " -- " + jarr);

		// if ( jobj ) {
		//
		// JsonObject obj = je.getAsJsonObject();
		// for ( Map.Entry< String, JsonElement > item : obj.entrySet() ) {
		// // item.getKey()
		// }
		// }

	}

}
