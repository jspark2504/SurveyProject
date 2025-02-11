package com.sample.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("test API")
                .description("test API");

        return new OpenAPI()
                .info(info);
    }

    @Bean
    public GroupedOpenApi apiGroup(){
        return GroupedOpenApi.builder()
                .group("sample")
                .pathsToExclude("/testset")
                .build();
    }
}
