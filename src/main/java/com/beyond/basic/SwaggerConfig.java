package com.beyond.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.data.relational.core.sql.StatementBuilder.select;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    // Docket : Swagger 구성의 핵심 기능 클래스
    // Docket 을 리텀함으로서 싱글톤 객체로 생성.
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() // 어떤 컨트롤러 또는 어떤 api 를 swagger 문서에 포함시킬지 선택.
                // 모든 RequestHandler(Controller)를 문서화 대상으로 선택한다는 설정.
                .apis(RequestHandlerSelectors.any())
                // 특정 path 만 문서화 대상으로 하겠다! 라는 설정.
                .paths(PathSelectors.ant("/rest/**")) // * : 보통 직계까지만, ** : 자손까지 포함
                .build();
    }
}
