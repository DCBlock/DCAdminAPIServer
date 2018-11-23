package com.digicap.dcblock.admin.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicap.dcblock.admin.dao.CategoryDAO;
import com.digicap.dcblock.admin.model.Category;
import com.digicap.dcblock.admin.model.ResponseMessage;
import com.digicap.dcblock.admin.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
@RestController
public class CommonController {

	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public ResponseEntity<String> getCategoris(
			/*@RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Content-Type") String contentType,*/
			HttpServletRequest request) {

		Category category = new Category();
		category.setCategoryDetail(categoryDao.selectAllCategory());
		
		if(category.getCategoryDetail() != null) {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.put("Content-Type", Arrays.asList("application/json"));

			String response = "";
			try {
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(category);

				return new ResponseEntity<String>(response, headers, HttpStatus.OK);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/categories")
	public ResponseEntity<String> postCategoris(
			/*@RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Content-Type") String contentType,*/
			@RequestBody String requestBody,
			HttpServletRequest request) {

		ObjectMapper mapper = new ObjectMapper();
		Category category = new Category();
		int retVal = 0;
		
		try {
			category = mapper.readValue(requestBody, Category.class);
			//String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(category);
			//System.out.println("Request : POST /api/categories/");
			//System.out.println(jsonStr);
			
			retVal = categoryService.modifyCategory(category);
			System.out.println("POST Categoryes : " + retVal);
			
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.put("Content-Type", Arrays.asList("application/json"));
			ResponseMessage resMessage = new ResponseMessage();
			resMessage.setCode(HttpStatus.OK.value());
			resMessage.setReason("success");
			
			String response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);
			
			return new ResponseEntity<String>(response, headers, HttpStatus.OK);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/api/categories/{code}")
	public ResponseEntity<String> deleteCategoris(
			/*@RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Content-Type") String contentType,*/
			@PathVariable("code") int code, 
			HttpServletRequest request) {

		
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
