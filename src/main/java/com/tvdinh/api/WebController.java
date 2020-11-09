package com.tvdinh.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvdinh.entity.UserEntity;

@RestController(value = "homeApiControllerOfWeb")
public class WebController {
	
	
	@PostMapping("/api/auth/signup")
	public UserEntity signup(@RequestBody UserEntity user) {
		
		return null;
	}
	
	@PostMapping("/api/auth/signin")
	public UserEntity signin(@RequestBody UserEntity user) {
		
		return null;
	}
	
	@GetMapping("/api/user/me")
	public UserEntity getUserDetail(@RequestBody Long id) {
		
		return null;
	}
	
	
	

}
