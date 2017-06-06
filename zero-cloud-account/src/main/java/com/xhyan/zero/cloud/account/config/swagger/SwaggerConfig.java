package com.xhyan.zero.cloud.account.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 配置文件
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SPRING_WEB)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xhyan.zero.cloud.account.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Zero-Cloud使用Swagger2构建的API")
                .description("")
                .termsOfServiceUrl("https://github.com/xhyan/zero-cloud")
                .contact(new Contact("xhyan", "https://github.com/xhyan/zero-cloud", "343440797@qq.com"))
                .version("v1.0")
                .build();
    }
}
