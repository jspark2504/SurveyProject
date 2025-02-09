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
public class QuestionDto {
	// 설문 템플 기준으로 질문이 여러개
	// 사용자에 대한 답변은... 또 따로 테이블 만들어서 
	// 답변 ID로 key 잡고 사용자 별로...
	// AnswerDto -> 답변 Dto로 만들자
	
	//PK
	@Schema(description = "질문ID", example = "")
    private Integer questionId;
	
	//FK
	@Schema(description = "설문ID", example = "")
    private Integer surveyId;
	
//	//ex : T : 텍스트, S : 단일선택, M : 다중 선택
//	@Schema(description = "질문타입", example = "")
//    private String questionType;
	
	@Schema(description = "질문", example = "")
    private String question;
	
//	// 나중에 순서 변경할 때 씀.
//	@Schema(description = "질문 순번", example = "")
//    private Integer questionOrder;
//	
//	// 빈 값인지 여부..? 
//	@Schema(description = "필수 질문", example = "")
//    private Boolean isRequired;
//	
//	// 통계여부 질문인지 
//	// 답변률 : 답변 갯수로 측정 : 답변 없을 경우는 Anser 테이블에서 저장 X 
//	// 평균 : 답변(나이 등) 에 대한 숫자 평균 
//	@Schema(description = "통계성 여부 질문 flag", example = "")
//    private Boolean isStatistical;

}
