package com.tvdinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvdinh.authentication.JwtTokenProvider;
import com.tvdinh.entity.CropEntity;
import com.tvdinh.entity.DeviceEntity;
import com.tvdinh.entity.PlantEntity;
import com.tvdinh.entity.SensorDataEnity;
import com.tvdinh.entity.UserEntity;
import com.tvdinh.payloads.JwtResponse;
import com.tvdinh.payloads.LoginRequest;
import com.tvdinh.service.IDeviceService;
import com.tvdinh.service.IPlantService;
import com.tvdinh.service.IUserService;

@RestController(value = "homeApiControllerOfWeb")
public class WebController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IPlantService plantService;
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
	
	
	@PostMapping("/api/auth/signup")
	public UserEntity signup(@RequestBody UserEntity user) {
		UserEntity userEntity=userService.save(user);
		return userEntity;
	}
	/*
	@PostMapping("/api/auth/signin")
	public UserEntity signin(@RequestBody UserEntity user) {
		return userService.findByUsernameAndPassword(user);
	}*/
	@PostMapping("/api/auth/signin")
	public JwtResponse  signin( @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()));
		// Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateJwtToken(authentication);
		return new JwtResponse(jwt,loginRequest.getUsername());
	}
	
	@GetMapping("/api/user/me")
	public UserEntity getUserDetail(@RequestBody Long id) {
		
		return userService.findById(id);
	}

	@GetMapping("/api/listuser")
	public List<UserEntity> getAllUser(){
		
		return userService.findAll();
	}
	
	
	
	@GetMapping("/api/crop")
	public List<CropEntity> getAllCrop(){
		
		return null;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/crop/{cropId}")
	public CropEntity getCropDetails(@PathVariable("cropId") Long id) {
		
		return null;
	}
	
	@PostMapping("/api/crop")
	public CropEntity createCrop(@RequestBody CropEntity crop) {
		return null;
	}
	
	@DeleteMapping("/api/crop")
	public Boolean deleteCrop(@RequestBody Long id) {
		return null;
	}
	
	@PostMapping("/api/crop/stop")
	public CropEntity stopCrop(@RequestBody CropEntity crop) {
		return null;
	}
	
	
	@GetMapping("/api/device")
	public List<DeviceEntity> getAllDevice(){
		
		return null;
	}
	
	@GetMapping("/api/device/{deviceId}")
	public DeviceEntity getDeviceDetails(@PathVariable("deviceId") Long id) {
		
		return null;
	}
	
	@GetMapping("/api/device/control")
	public DeviceEntity controlDevice() {
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/api/plant")
	public List<PlantEntity> getAllPlant(){
		
		return plantService.findAll();
	}
	
	/**
	 * 1 device ESP32->Sẽ có nhiều sensor đi theo
	 * 1 sensor->có nhiều data(Do ta liên tục gửi dữ liệu)
	 * @return tập hợp dữ liệu tất cả sensor lần cuối cùng cập nhật ứng với device
	 */
	
	
	@GetMapping("/api/sensor/{deviceId}")
	public DeviceEntity getAllSensorDataInPoneDevice(@PathVariable("deviceId") Long id){
		
		return deviceService.getAllDataSensor(id);
	}
	
	
	

}
