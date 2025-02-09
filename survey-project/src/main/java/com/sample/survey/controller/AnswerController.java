package com.sample.survey.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.survey.dto.AnswerDto;
import com.sample.survey.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/Answer")
public class AnswerController {
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    private final AnswerService AnswerService;

    public AnswerController(AnswerService AnswerService) {
        this.AnswerService = AnswerService;
    }
    
    // 설문 답변 등록
    @PostMapping("/answers")
    @Operation(summary = "설문자 답변 등록", description = "")
    public ResponseEntity<String> createAnswer(@RequestBody AnswerDto answerDto) {
        AnswerService.createAnswer(answerDto);
        return ResponseEntity.ok("Answer created successfully");
    }
    
}
