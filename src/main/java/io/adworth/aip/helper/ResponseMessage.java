package io.adworth.aip.helper;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {
	public static ResponseEntity<HashMap<String, Object>> response(Object message, HttpStatus status) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (status == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		result.put("message", message);
		result.put("timestamp", new Date());
		result.put("status", status.value());
		result.put("error", null);
		if (status.value() != 200) {
			result.put("error", status.getReasonPhrase());
		}
		return new ResponseEntity<HashMap<String, Object>>(result, status);
	}
	
	public static ResponseEntity<HashMap<String, Object>> response(Object message, HttpStatus status, String token) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (status == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		result.put("message", message);
		result.put("timestamp", new Date());
		result.put("status", status.value());
		result.put("token", token);
		result.put("error", null);
		if (status.value() != 200) {
			result.put("error", status.getReasonPhrase());
		}
		return new ResponseEntity<HashMap<String, Object>>(result, status);
	}
}
