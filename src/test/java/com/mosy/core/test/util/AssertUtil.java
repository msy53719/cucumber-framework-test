package com.mosy.core.test.util;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;

public class AssertUtil {

	public static void assertResToMap(String str, Map<String, String> map) {
		JsonPath jsonpath = new JsonPath(str);
		for (Entry<String, String> entery : map.entrySet()) {
			Assert.assertEquals(entery.getValue(), jsonpath.getString(entery.getKey()));
		}
	}
}