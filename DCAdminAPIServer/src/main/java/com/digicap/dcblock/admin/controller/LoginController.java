package com.digicap.dcblock.admin.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.digicap.dcblock.admin.model.AdminDetail;
import com.digicap.dcblock.admin.model.ResponseMessage;
import com.digicap.dcblock.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody AdminDetail adminDetail, 
			@RequestHeader(value = "Content-Type") String contentType,
			HttpServletRequest req) {
		int retVal = 0;

		System.out.println("ContentType : " + contentType);
		
		retVal = adminService.checkLogin(adminDetail);

		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.put("Content-Type", Arrays.asList("application/json"));
		String response = "";
		ResponseMessage resMessage = new ResponseMessage();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		if (retVal != 0) {
			try {
				resMessage.setCode(HttpStatus.OK.value());
				resMessage.setReason("success");
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);
				httpStatus = HttpStatus.OK;
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		} else {
			try {
				resMessage.setCode(HttpStatus.UNAUTHORIZED.value());
				resMessage.setReason("UNAUTHORIZED");
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);
				httpStatus = HttpStatus.UNAUTHORIZED;
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<String>(response, headers, httpStatus);
	}
}
