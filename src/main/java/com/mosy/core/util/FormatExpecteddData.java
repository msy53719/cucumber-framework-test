package com.mosy.core.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mosy.core.contant.FeatureContant;

public class FormatExpecteddData {
	public static Map<String, String> getMap(Map<String, String> map) {
		Map<String, String> formatMap = new LinkedHashMap<>();
		for (Entry<String, String> entery : map.entrySet()) {
			formatMap.put(entery.getKey(), entery.getValue());
		}
		formatMap.remove(FeatureContant.RESPONSEDATAKEY);
		return formatMap;
	}
}
