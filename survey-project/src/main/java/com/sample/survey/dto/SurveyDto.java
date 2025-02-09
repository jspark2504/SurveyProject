package com.sample.survey.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class SurveyDto {
	
	//PK
	@Schema(description = "설문ID", example = "")
    private Integer surveyId;
	
	@Schema(description = "설문 제목", example = "title")
    private String title;
	
	@Schema(description = "설문 설명", example = "description")
    private String description;
	
	@Schema(description = "설문 시작일", example = "")
    private Date startDate;
	
	@Schema(description = "설문 마감일", example = "")
    private Date endDate;
	
	@Schema(description = "closingMessage", example = "")
    private String closingMessage;
	
	@Schema(description = "createdAt", example = "")
	private Date createdAt;
	
	@Schema(description = "creator", example = "")
	private String creator;
	
	@Schema(description = "updatedAt", example = "")
	private Date updatedAt;
	
	@Schema(description = "category", example = "")
	private String category;
	
	@Schema(description = "maxResponses", example = "")
	private int maxResponses;
	
	@Schema(description = "uuid", example = "")
	private String uuid;

}