package io.adworth.aip.controller;

import io.adworth.aip.helper.ResponseMessage;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

@RestController
public class PingController {
	@GetMapping("/health")
	public ResponseEntity<?> status(){
		return ResponseMessage.response("Server OK", HttpStatus.OK);
	}
}