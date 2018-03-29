package io.adworth.aip.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@RestController
public class PingController {
	
	@RequestMapping("/health")
	public Map<String, String> status(){
		return Collections.singletonMap("Status", "OK");
	}
}