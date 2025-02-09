package com.sample.survey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.survey.dto.AnswerDto;
import com.sample.survey.dto.SurveyDto;
import com.sample.survey.mapper.SurveyMapper;

@Service
public class SurveyService {
    private final SurveyMapper surveyMapper;
    private final ObjectMapper objectMapper;

    public SurveyService(SurveyMapper surveyMapper, ObjectMapper objectMapper) {
        this.surveyMapper = surveyMapper;
        this.objectMapper = objectMapper;
    }
    
    public List<SurveyDto> getSurvey(int offset, int limit) {
        return surveyMapper.selectSurvey(offset, limit);
    }

    // UUID로 설문조사 조회
    public Optional<SurveyDto> getSurveyByUuid(String uuid) {
        return surveyMapper.findByUuid(uuid);
    }

    public List<String> getQuestionTitlesByUuid(String uuid) {
        List<String> questionTitles = new ArrayList<>();

        // DB에서 UUID를 기준으로 질문 데이터 조회
        List<String> questions = surveyMapper.findQuestionsByUuid(uuid);

        for (String questionJson : questions) {
            try {
                // JSON 파싱
                JsonNode jsonNode = objectMapper.readTree(questionJson);
                String title = jsonNode.get("title").asText(); // "title" 값 추출
                questionTitles.add(title);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return questionTitles;
    }
    
    /**
     * 설문 응답 데이터 저장
     */
    public void saveSurveyResponse(String uuid, String answer, String respondentName, String respondentEmail) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUuid(uuid);
        answerDto.setAnswer(answer);
        answerDto.setRespondentEmail(respondentEmail);
        answerDto.setRespondentName(respondentName);

        surveyMapper.insertSurveyResponse(answerDto);
    }

    
}
