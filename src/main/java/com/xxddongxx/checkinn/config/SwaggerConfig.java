package com.xxddongxx.checkinn.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("CheckInn api docs")
                        .version("1.0")
                        .description("CheckInn REST API 문서입니다."))
                .tags(List.of(
                        new Tag().name("01. Member").description("Member API"),
                        new Tag().name("02. Accommodation").description("Accommodation API")
                ));
    }

    @Bean
    public GroupedOpenApi api(){
        String[] paths = {"/api/v1/**"};
        String[] packagesToScan = {"com.xxddongxx.checkinn"};
        return GroupedOpenApi.builder()
                .group("CheckInn api docs")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
}
