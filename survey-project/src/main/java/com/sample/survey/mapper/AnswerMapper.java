package com.sample.survey.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sample.survey.dto.AnswerDto;

@Mapper
public interface AnswerMapper {
   
	void insertAnswer(AnswerDto answerDto);
    
}