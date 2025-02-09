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
public class testDto {
		@Schema(description = "테스트", example = "John1")
	 	private String additionalProp1;

		@Schema(description = "테스트2", example = "Doe2")
	    private String additionalProp2;

	    public String getAdditionalProp1() {
	        return additionalProp1;
	    }

	    public void setAdditionalProp1(String additionalProp1) {
	        this.additionalProp1 = additionalProp1;
	    }

	    public String getAdditionalProp2() {
	        return additionalProp2;
	    }

	    public void setAdditionalProp2(String additionalProp2) {
	        this.additionalProp2 = additionalProp2;
	    }
}
