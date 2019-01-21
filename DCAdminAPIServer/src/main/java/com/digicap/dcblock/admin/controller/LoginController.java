package com.digicap.dcblock.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.digicap.dcblock.admin.model.AdminDetail;
import com.digicap.dcblock.admin.model.ResponseMessage;
import com.digicap.dcblock.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySource("classpath:/config/common.properties")
@RestController
public class LoginController {

	@Value("${HMAC_KEY}")
	private String hmacKey;

	@Value("${EXPIRE_TIME}")
	private int expireTime;
	
	@Value("${ISSUER}")
	private String issuer;
	
	@Deprecated
	@Value("${SHA_256_KEY}")
	private String shaKey;

	@Autowired
	private AdminService adminService;

	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody AdminDetail adminDetail,
			@RequestHeader(value = "Content-Type") String contentType, HttpServletRequest req) throws JsonProcessingException {
		
		String response = "";
		ResponseMessage resMessage = new ResponseMessage();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		//headers.put("Content-Type", Arrays.asList("application/json"));
		headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(adminDetail == null) {
			resMessage.setCode(HttpStatus.BAD_REQUEST.value());
			resMessage.setReason("BAD REQUEST");
			
			return new ResponseEntity<String>(response, headers, httpStatus);
		}else {
			//String sha256 = DigestUtils.sha256Hex(adminDetail.getPassword() + shaKey);
			//adminDetail.setPassword(sha256);			
			
			map = adminService.checkLogin(adminDetail);	
		}
		
		//System.out.println("Return Value(map) : " + map);

		if (map != null) {
			try {
				int expTime = expireTime * 1000;

				Date isu = new Date(System.currentTimeMillis());
				Date exp = new Date(System.currentTimeMillis() + expTime);
				
				Algorithm algHmac = Algorithm.HMAC256(hmacKey);
				HashMap<String, Object> headerClaims = new HashMap<String, Object>();
				headerClaims.put("typ", "JWT");
				headerClaims.put("alg", "HS256");

				//TODO:Grant Setting
				List<String> grantList = new ArrayList<String>();
				grantList = (List<String>) map.get("grant");
				
				String[] grantArr = null;
				if(grantList.size() > 0) {
					grantArr = grantList.toArray(new String[grantList.size()]);
				}else {
					grantArr = new String[0];
				}

				//TODO: .withClaim("nonce", new DCRandomUtils().getNonce(12))
				/*
				 * TODO: 20181207 / role -> scope 변경, grant -> authorities 변경
				 * manageuser -> ROLE_MANAGEUSER
				 * managemenu -> ROLE_MANAGEMENU
				 * viewstatiscs -> ROLE_VIEWSTATISCS
				 * manageadmin -> ROLE_MANAGEADMIN
				 */
				String token = JWT.create().withIssuer(issuer)
						.withHeader(headerClaims)
						.withExpiresAt(exp)
						.withIssuedAt(isu)
						.withClaim("id", adminDetail.getId())
						.withClaim("scope", (String) map.get("scope"))
						.withArrayClaim("authorities", grantArr)
						.withClaim("company", (String) map.get("company"))
						.sign(algHmac);

				//headers.put("Authorization", Arrays.asList("Bearer " + token));

				//resMessage.setCode(HttpStatus.OK.value());
				//resMessage.setReason("success");
				HashMap<String, Object> retMap = new HashMap<String, Object>();
				retMap.put("access_token", token);
				retMap.put("token_type", "bearer");
				retMap.put("expires_at", exp);
				httpStatus = HttpStatus.OK;

				response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(retMap);

			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			resMessage.setCode(HttpStatus.UNAUTHORIZED.value());
			resMessage.setReason("UNAUTHORIZED");
			httpStatus = HttpStatus.UNAUTHORIZED;
			
			response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);

		}

		
		return new ResponseEntity<String>(response, headers, httpStatus);
	}

	@PostMapping("/api/validation")
	public ResponseEntity<String> validationToken(
			@RequestHeader(value = "Authorization") String auth, HttpServletRequest req) {
		
		String[] authJwt = auth.split("Bearer ");
		String authToken = authJwt[1];
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		//headers.put("Content-Type", Arrays.asList("application/json"));
		headers.put("Content-Type", Arrays.asList("application/json; charset=utf-8"));

		String response = "";
		ResponseMessage resMessage = new ResponseMessage();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		Algorithm algHmac;
		try {
			algHmac = Algorithm.HMAC256(hmacKey);
			DecodedJWT decodeJwt = JWT.require(algHmac).build().verify(authToken);
			
			if(decodeJwt.getIssuer().equals(issuer)) {
				resMessage.setCode(HttpStatus.OK.value());
				resMessage.setReason("success");
				httpStatus = HttpStatus.OK;

			}
		} catch (JWTVerificationException e) {
			//System.out.println("Error : " + e.getMessage());
			resMessage.setCode(HttpStatus.BAD_REQUEST.value());
			resMessage.setReason("TOKEN_EXPIRED");

			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resMessage);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(response, headers, httpStatus);
	}
}
