package com.sample.survey.dto;

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
public class RespondentDto {
	
	//PK
	@Schema(description = "응답자ID", example = "")
    private Integer responseId;
	
	//FK 가장 첫번째 답변
	@Schema(description = "답변ID", example = "")
    private Integer questionId;

	@Schema(description = "답변시간", example = "")
    private String responseTime;
	
	@Schema(description = "답변자이메일", example = "")
	private String responseEmail;
	
	
}