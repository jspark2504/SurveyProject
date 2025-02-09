package com.sample.survey.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.sample.survey.dto.AnswerDto;
import com.sample.survey.dto.SurveyDto;

@Mapper
public interface SurveyMapper {
    
    List<SurveyDto> selectSurvey(@Param("offset") int offset, @Param("limit") int limit);

	Optional<SurveyDto> findByUuid(@Param("uuid") String uuid);

	List<String> findQuestionsByUuid(@Param("uuid") String uuid);
	
    void insertSurveyResponse(AnswerDto answerDto);

}