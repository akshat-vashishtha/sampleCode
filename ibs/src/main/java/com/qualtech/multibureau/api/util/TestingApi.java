package com.qualtech.multibureau.api.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestingApi {

	public static void main(String[] args) {

		
		
	}
	

	@SuppressWarnings("unchecked")
	public  static JSONObject keysToLowerCase(JSONObject object) throws JSONException {
		JSONObject jsonObject=new JSONObject();
		
		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = keysToLowerCase((JSONObject) value);
			}
			jsonObject.put(key.replaceAll("-","_").toLowerCase().replaceAll(" ","_"), value);
		}
		return jsonObject;
	}
	public static  List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = keysToLowerCase((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}


}
