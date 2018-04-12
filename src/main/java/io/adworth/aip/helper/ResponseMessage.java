package io.adworth.aip.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static void setSecurityResponse(HttpServletResponse res, Object message, Object error, String token, HttpStatus status) throws JsonProcessingException, IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("error", error);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String text = formatter.format(new Date());
		result.put("timestamp", text);
		result.put("status", status.value());
		result.put("token", token);
		res.setStatus(status.value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(result));
	}
}
