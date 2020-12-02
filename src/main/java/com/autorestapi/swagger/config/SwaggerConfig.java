package com.autorestapi.swagger.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @Since: Created  in 11:31 2020/12/1
 * @Author: Ljx
 * @Decription:
 */
@Configuration
@EnableSwagger2     //开启Swagger2
public class SwaggerConfig {

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要启动swagger的环境
        Profiles profiles = Profiles.of("dev","test");
        //判断当前环境是否处在启动swagger的环境中 Environment系统环境变量
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                /**
                 *配置Swagger信息
                 */
                .apiInfo(apiInfo())
                .groupName("json-lu")//swagger分组，一个Docket实例就是一个分组
                /**
                 * 配置是否启动swagger
                 *  如果为false,Swagger在浏览器中不能访问
                 */
                .enable(flag)//根据系统环境决定是否启动swagger
                .select()
                /**
                 * RequestHandlerSelectors,配置要扫描接口的方式
                 *      basePackage("包路径"):指定要扫描的包
                 *      any():扫描全部
                 *      none()：不扫描
                 *      withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                 *      withMethodAnnotation:扫描方法上的注解
                 */
                //.apis(RequestHandlerSelectors.basePackage("com.autorestapi.swagger.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                /**
                 *  过滤接口路径
                 *      PathSelectors,配置扫描接口中需要被过滤掉的路径
                 *      ant("访问路径"):过滤该访问路径的接口
                 *      any():过滤全部
                 *      none():不过滤
                 *      regex(正则):按照正则表达式过滤路径
                 */
                .paths(PathSelectors.ant("/user/**"))
                .build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes());
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");//A分组
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");//B分组
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");//C分组
    }

    //配置Swagger信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("json-lu", "https://gitee.com/json-lu/", "freedStyle@163.com");
        return  new ApiInfo("My Swagger接口文档",
                "first Swagger",
                "1.0",
                "https://gitee.com/json-lu/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }


}
