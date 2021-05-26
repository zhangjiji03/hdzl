package com.aiboatapi.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 项目名称:     AiBoatApi
 * 类名称:       SwaggerConfig
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/5/26 10:55
 * 版本:         1.0
 */
public class SwaggerConfig {

    @Bean("AiBoatApi")
    @Order(value = 1)
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.aiboatapi.one.controller"))
                //为有@ApiOperation注解的方法生成API文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AiBoatApi项目接口文档")
                .description("AiBoatApi项目接口文档")
                .contact("zhangzhe")
                .version("1.0")
                .build();
    }

}
