package com.sample.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan("com.sample")
public class SurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

}
