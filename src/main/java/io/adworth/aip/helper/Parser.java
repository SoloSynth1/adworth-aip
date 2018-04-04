package io.adworth.aip.helper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Parser {
	public static ArrayList<Integer> json2array(String json_str) {
		try {
			JSONObject obj = new JSONObject(json_str);
			JSONArray arr = obj.getJSONArray("array");
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
	
	public static ResponseEntity<?> parseError() {
		return ResponseMessage.response("One or more element(s) cannot be parsed into integers.", HttpStatus.BAD_REQUEST);
	}
}
