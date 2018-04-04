package io.adworth.aip.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.adworth.aip.helper.ResponseMessage;
import io.adworth.aip.helper.Sort;

@RestController
@RequestMapping("/array")
public class ArrayController {
	
	private static ArrayList<Integer> intList = new ArrayList<Integer>();
	
//	@PutMapping(value = "/put")
	@PutMapping()
	public ResponseEntity<?> putArray(@RequestBody String json_str){
		try {
			JSONObject obj = new JSONObject(json_str);
			JSONArray arr = obj.getJSONArray("array");
			ArrayList<Integer> rectified_arr = new ArrayList<Integer>();
			for ( int i = 0; i < arr.length(); i++) {
				if (arr.get(i) instanceof Integer){
					rectified_arr.add(arr.getInt(i));
				} else{
					rectified_arr.add(Integer.parseInt(arr.getString(i)));
				}
			}
			synchronized (intList){
				intList.addAll(rectified_arr);
			}
		} catch (Exception e){
			return ResponseMessage.response("One or more element(s) cannot be parsed into integers.", HttpStatus.BAD_REQUEST);
		}
		return ResponseMessage.response("PUT successful, " + intList, HttpStatus.OK);
	}
	
	@PostMapping()
	public synchronized ResponseEntity<?> sortArray(){
		Sort.mergeSort(intList);
		return ResponseMessage.response("POST successful, " + intList, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> getArray(){
		return ResponseMessage.response(Collections.singletonMap("array", intList), HttpStatus.OK);
	}
}
