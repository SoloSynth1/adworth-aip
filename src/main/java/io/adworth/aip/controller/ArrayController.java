package io.adworth.aip.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.adworth.aip.helper.ResponseMessage;

@RestController
public class ArrayController {
	
	private static ArrayList<Integer> intList = new ArrayList<Integer>();
	
	@PutMapping(value = "/array/put")
	public ResponseEntity<?> putArray(@RequestBody String json_str){
		try {
			JSONObject obj = new JSONObject(json_str);
			JSONArray arr = obj.getJSONArray("array");
			for ( int i = 0; i < arr.length(); i++) {
				if (arr.get(i) instanceof Integer){
					intList.add(arr.getInt(i));
				} else{
					intList.add(Integer.parseInt(arr.getString(i)));
				}
			}
		} catch (Exception e){
			return ResponseMessage.response(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseMessage.response("PUT successful, " + intList, HttpStatus.OK);
	}
	
	@PostMapping("/array/post/{num}")
	public ResponseEntity<?> postArray(){
		return null;
	}
	
	@GetMapping("/array")
	public ResponseEntity<?> getArray(){
		return null;
	}
}
