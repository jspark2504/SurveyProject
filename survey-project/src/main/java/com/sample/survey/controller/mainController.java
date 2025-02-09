package com.sample.survey.controller;


import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.survey.dto.testDto;
import com.sample.survey.dto.testDto2;
import com.sample.survey.service.mainService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
public class mainController {

    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    @Autowired(required = true)
	private mainService mainService;
    public mainController(mainService mainService) {
        this.mainService = mainService;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/test")
    public String login() {
    	return"test";
    }
    
    @PostMapping("/test/sendAuth")
    @Operation(summary = "발송 테스트", description = "테스트")
	public ResponseEntity usercheck(@RequestBody testDto test, HttpSession session) throws Exception {
    	HttpStatus hStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders hHeader = new HttpHeaders();
		hHeader.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		ObjectMapper objMapper = new ObjectMapper();
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //결과메세지맵

        session.setAttribute("smsAuthInfo", test);

        
        hStatus = HttpStatus.OK;
        
    	return new ResponseEntity(objMapper.writeValueAsString(resultMap), hHeader, hStatus);
	}
    
    // 인증번호 확인 
    @PostMapping("/test/checkAuth")
    @Operation(summary = "인증번호 확인", description = "")
	public ResponseEntity smsCheck(HttpSession session, testDto2 resetReqDto) throws Exception {
    	
    	HttpStatus hStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders hHeader = new HttpHeaders();
		hHeader.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		// 3. 결과값 전달
		ObjectMapper objMapper = new ObjectMapper();
        HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		String userId = "test";

		/* 인증번호 확인 */
			int smsAuthInfo = mainService.checkAuth(session);
        
	        hStatus = HttpStatus.OK;

		return new ResponseEntity(objMapper.writeValueAsString(resultMap), hHeader, hStatus);
    }
}
