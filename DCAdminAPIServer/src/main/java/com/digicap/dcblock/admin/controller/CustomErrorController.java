package com.digicap.dcblock.admin.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digicap.dcblock.admin.model.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		//headers.put("Content-Type", Arrays.asList("application/json"));
		headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

    	String response = "";
		try {
			ResponseMessage resMessage = new ResponseMessage();
			resMessage.setCode(HttpStatus.NOT_FOUND.value());
			resMessage.setReason("Not Found");
			
			response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(response, headers, HttpStatus.NOT_FOUND);
    }
    
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
