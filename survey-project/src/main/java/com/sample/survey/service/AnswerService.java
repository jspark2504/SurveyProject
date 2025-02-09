package com.sample.survey.service;

import org.springframework.stereotype.Service;

import com.sample.survey.dto.AnswerDto;
import com.sample.survey.mapper.AnswerMapper;

@Service
public class AnswerService {
    private final AnswerMapper answerMapper;

    public AnswerService(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    public void createAnswer(AnswerDto answerDto) {
    	answerMapper.insertAnswer(answerDto);
    }
    
}
