package com.sample.survey.service;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.sample.survey.dto.testDto;

import jakarta.servlet.http.HttpSession;

@Service
public class mainService {
	
	
	 public int sendAuth(HashMap<String, Object> acraUserCheckReqDto, String userId, String authType){
	        String smsAuthNumber = RandomStringUtils.randomNumeric(6);
	        // 아이디하고, 인증번호 로그 남기기 
	        int reuslt = 0;
	        HashMap<String, Object> map = new HashMap<String, Object>(); 
	        
	        
	        return 1;
	    }
	 
	 
	 public int checkAuth(HttpSession session) {

		 testDto smsAuthInfo = (testDto) session.getAttribute("smsAuthInfo");

	        System.out.println( smsAuthInfo.toString());
	        System.out.println( smsAuthInfo.getAdditionalProp1());
	        System.out.println( smsAuthInfo.getAdditionalProp2());

	        return 1;
	    }
}
