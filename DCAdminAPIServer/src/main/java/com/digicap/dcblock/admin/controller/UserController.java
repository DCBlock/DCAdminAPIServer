package com.digicap.dcblock.admin.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digicap.dcblock.admin.dao.UserDAO;
import com.digicap.dcblock.admin.model.ResponseMessage;
import com.digicap.dcblock.admin.model.User;
import com.digicap.dcblock.admin.model.UserDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	private UserDAO userDao;

	@GetMapping("/api/users")
	public ResponseEntity<String> getUsersByRfid(@RequestParam Map<String, String> parameter, HttpServletRequest req) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		//headers.put("Content-Type", Arrays.asList("application/json"));
		headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

		String response = "";
		
		System.out.println("Request Addr : " + req.getRemoteAddr());
		
		String rfid = parameter.get("rfid").trim();
		UserDetail userDetail = new UserDetail();

		if (rfid == null) {
			// TODO: email null 처리
			String email = parameter.get("email").trim();

			// TODO: User Detail null 처리 혹은 Service 단에서 Exception 처리
			userDetail = userDao.selectUserByEmail(email);

		} else {
			userDetail = userDao.selectUserByRfid(rfid);
		}

		if(userDetail != null) {
			try {
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userDetail);

				return new ResponseEntity<String>(response, headers, HttpStatus.OK);
			} catch (JsonProcessingException e) {
				e.printStackTrace(); 
			}
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/api/users/page/{paging}")
	public ResponseEntity<String> getUserInfoPaging(@PathVariable("paging") int paging, HttpServletRequest req){
		User user = new User();
		List<UserDetail> userList = userDao.selectUserPaging(paging);

		if (userList != null) {
			user.setUserDetail(userList);

			MultiValueMap<String, String> headers = new HttpHeaders();
			//headers.put("Content-Type", Arrays.asList("application/json"));
			headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

			String response = "";
			try {
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);

				return new ResponseEntity<String>(response, headers, HttpStatus.OK);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/users")
	public ResponseEntity<String> postUser(@RequestBody UserDetail userDetail, HttpServletRequest req) {
		int retVal = 0;

		if (userDetail != null) {
			retVal = userDao.insertUser(userDetail);

			if (retVal != 0) {
				MultiValueMap<String, String> headers = new HttpHeaders();
				//headers.put("Content-Type", Arrays.asList("application/json"));
				headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

				String response = "";
				try {
					ResponseMessage resMessage = new ResponseMessage();
					resMessage.setCode(HttpStatus.OK.value());
					resMessage.setReason("success");

					response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);

					return new ResponseEntity<String>(response, headers, HttpStatus.OK);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			}
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping("/api/users/{index}")
	public ResponseEntity<String> patchUser(@PathVariable("index") String index, 
			@RequestBody UserDetail userDetail, HttpServletRequest req){
		
		int retVal = 0;
		
		//TODO: id Check Service 추가 할 것
		
		if(userDetail != null) {
			retVal = userDao.updateUser(userDetail);
			
			if(retVal != 0) {
				MultiValueMap<String, String> headers = new HttpHeaders();
				//headers.put("Content-Type", Arrays.asList("application/json"));
				headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

				String response = "";
				try {
					ResponseMessage resMessage = new ResponseMessage();
					resMessage.setCode(HttpStatus.OK.value());
					resMessage.setReason("success");
					
					response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);

					return new ResponseEntity<String>(response, headers, HttpStatus.OK);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/api/users/{index}")
	public ResponseEntity<String> deleteUser(@PathVariable("index") BigInteger index, HttpServletRequest req){
		int retVal = 0;
		if(index != null) {
			retVal = userDao.deleteUser(index);
			
			if(retVal != 0) {
				MultiValueMap<String, String> headers = new HttpHeaders();
				//headers.put("Content-Type", Arrays.asList("application/json"));
				headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

				String response = "";
				try {
					ResponseMessage resMessage = new ResponseMessage();
					resMessage.setCode(HttpStatus.OK.value());
					resMessage.setReason("success");
					
					response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);

					return new ResponseEntity<String>(response, headers, HttpStatus.OK);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	/*
	@GetMapping("/api/users")
	public ResponseEntity<String> getUsersByRfid(@RequestParam Map<String, String> parameter, HttpServletRequest req) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.put("Content-Type", Arrays.asList("application/json"));

		String response = "";

		if (parameter.isEmpty()) {

			User user = new User();
			List<UserDetail> userList = userDao.selectUserAll();

			if (userList != null) {
				user.setUserDetail(userList);

				try {
					response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);

					return new ResponseEntity<String>(response, headers, HttpStatus.OK);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			}

		} else {
			String rfid = parameter.get("rfid").trim();
			UserDetail userDetail = new UserDetail();

			if (rfid == null) {
				// TODO: email null 처리
				String email = parameter.get("email").trim();

				// TODO: User Detail null 처리 혹은 Service 단에서 Exception 처리
				userDetail = userDao.selectUserByEmail(email);

			} else {
				userDetail = userDao.selectUserByRfid(rfid);
			}

			System.out.println("User : " + userDetail.toString());

			try {
				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userDetail);

				return new ResponseEntity<String>(response, headers, HttpStatus.OK);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	*/

}
