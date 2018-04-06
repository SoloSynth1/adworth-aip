package io.adworth.aip.helper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Parser {
	public static ArrayList<Integer> json2IntArray(String json_str, String col) {
		try {
			JSONObject obj = new JSONObject(json_str);
			JSONArray arr = obj.getJSONArray(col);
			ArrayList<Integer> rectifiedArray = new ArrayList<Integer>();
			for ( int i = 0; i < arr.length(); i++) {
				if (arr.get(i) instanceof Integer) {
					rectifiedArray.add(arr.getInt(i));
				} else {
					rectifiedArray.add(Integer.parseInt(arr.getString(i)));
				}
			}
			return rectifiedArray;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<String> json2StrArray(String json_str, String col) {
		try {
			JSONObject obj = new JSONObject(json_str);
			JSONArray arr = obj.getJSONArray(col);
			ArrayList<String> strArray = new ArrayList<String>();
			for ( int i = 0; i < arr.length(); i++) {
				strArray.add(arr.getString(i));
			}
			return strArray;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String json2Str(String json_str, String col) {
		try {
			JSONObject obj = new JSONObject(json_str);
			String content = obj.getString(col);
			return content;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ResponseEntity<?> parseError() {
		return ResponseMessage.response("One or more element(s) cannot be parsed into integers.", HttpStatus.BAD_REQUEST);
	}
}
