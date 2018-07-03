package com.mosy.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class MapTest {
	@Test
	public void mapTest() {
      Map<String, String> map=new LinkedHashMap<>();
      map.put("a", "1");
      map.put("b", "2");
      map.put("c", "3");
      map.put("d", "4");
      map.put("e", "5");
      map.remove("a");
      for (Entry<String, String> entry : map.entrySet()) {
		System.out.println(entry.getKey() + entry.getValue());
	}
	}

}
