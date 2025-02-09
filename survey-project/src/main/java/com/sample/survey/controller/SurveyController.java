package com.sample.survey.controller;


import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.survey.dto.SurveyDto;
import com.sample.survey.service.SurveyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    
    // 0. UUID 기반 설문조사 조회
    @GetMapping("/{uuid}")
    public String getSurveyByUuid(@PathVariable("uuid") String uuid, Model model, HttpSession session) {
        // UUID에 해당하는 설문조사 데이터 조회
    	 SurveyDto survey = surveyService.getSurveyByUuid(uuid)
                 .orElseThrow(() -> new IllegalArgumentException("Survey not found for UUID: " + uuid));
        System.out.println(survey.toString());
        // 세션(데이터 유지용)
        session.setAttribute("uuid", uuid);
        
        // 화면 전달용
        model.addAttribute("survey", survey);
        model.addAttribute("title", survey.getTitle());
        model.addAttribute("description", survey.getDescription());
        return "userInfo";
    }    

    // 1. 설문 시작 -> 여기서 list 보여줘야 됨. 
    @PostMapping("/start-survey")
    public String startSurvey(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              Model model, HttpSession session) {
        // 사용자 정보를 저장하고 설문조사 페이지로 이동
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        String uuid = (String) session.getAttribute("uuid");
        List<String> questionTitles = surveyService.getQuestionTitlesByUuid(uuid);
        System.out.println(uuid);

        // 사용자 이름과 이메일을 모델에 추가
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("questions", questionTitles);

        return "survey"; // survey.html 렌더링
    }

    // 2. 설문 입력 완료 및 설문 입력 테이블에저장 -> 로직 추가 해야 됨. form
    @PostMapping("/answer")
    public String submitSurvey(@RequestParam Map<String, String> responses,
                               Model model, HttpSession session) throws JsonProcessingException {
        // UUID, Name, Email 확인
    	String respondentName = (String) session.getAttribute("name");
    	String respondentEmail = (String) session.getAttribute("email");
    	String uuid = (String) session.getAttribute("uuid");
    	System.out.println("Name: " + respondentName);
    	System.out.println("Email: " + respondentEmail);
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 String answer = objectMapper.writeValueAsString(responses);
         System.out.println("Converted JSON: " + answer);

         surveyService.saveSurveyResponse(uuid, answer, respondentName, respondentEmail);
    	
        // 설문 응답 데이터를 처리 (예: DB에 저장)
        model.addAttribute("message", "Thank you for completing the survey!");
        
        return "result"; // 감사 페이지로 이동
    }
    
    
}