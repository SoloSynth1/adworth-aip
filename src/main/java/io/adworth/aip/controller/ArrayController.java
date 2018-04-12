package io.adworth.aip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.adworth.aip.helper.Parser;
import io.adworth.aip.helper.ResponseMessage;
import io.adworth.aip.helper.Sort;

@RestController
@RequestMapping("/api/array")
public class ArrayController {
	
	private static ArrayList<Integer> intList = new ArrayList<Integer>();
	
	@PutMapping()
	public ResponseEntity<?> putArray(@RequestBody String json_str) throws IOException {
		ArrayList<Integer> arr = Parser.json2IntArray(json_str, "array");
		if (arr == null) {
			return Parser.parseError();
		}
		synchronized (intList){
			intList.addAll(arr);
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
	
	@PostMapping("/getmaxmin")
	public ResponseEntity<?> getMax(@RequestBody String json_str){
		ArrayList<Integer> arr = Parser.json2IntArray(json_str, "array");
		Sort.mergeSort(arr);
		return ResponseMessage.response("POST successful, MAX: " + arr.get(arr.size()-1) + " , MIN: " + arr.get(0), HttpStatus.OK);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> deleteByElement(@RequestBody String json_str){
		Integer element = Parser.json2Int(json_str, "element");
		if (element == null) {
			return Parser.parseError();
		}
		intList.remove(element);
		return ResponseMessage.response("DELETE successful, " + intList, HttpStatus.OK);
	}
}
