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
public class AnswerDto {
	// 설문 템플 기준으로 질문이 여러개
	// 사용자에 대한 답변은... 또 따로 테이블 만들어서 
	// 답변 ID로 key 잡고 사용자 별로...
	// AnswerDto -> 답변 Dto로 만들자
	
	//PK
	@Schema(description = "답변ID", example = "")
    private int answerId;
	
	//FK
	@Schema(description = "uuid", example = "")
    private String uuid;

	@Schema(description = "답변", example = "")
	private String answer;
	
	@Schema(description = "responseTime", example = "")
	private Date responseTime;
	
	@Schema(description = "답변자 이름", example = "")
	private String respondentName;
	
	@Schema(description = "답변자 이메일", example = "")
	private String respondentEmail;
	
}
