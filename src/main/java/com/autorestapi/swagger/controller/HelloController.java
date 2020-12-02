package com.autorestapi.swagger.controller;

import com.autorestapi.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @Since: Created  in 11:24 2020/12/1
 * @Author: Ljx
 * @Decription:
 */
@Api(tags = "Hello控制类",description = "Hello控制类")
@RestController
public class HelloController {

    /**
     * 一个项目一定有一个/error请求(任意请求路径)
     *  所以有两个请求路径 /error /hello
     * @return
     */
    @GetMapping(value = "/user/hello")
    @ApiOperation(value = "hello方法")
    public String hello(){
        return "hello";
    }

    /**
     * ApiOperation参数
     * value：方法描述
     * httpMethod:方法请求类型
     * notes:方法注释
     * tags:方法作用（标签）
     * @return
     */
    @GetMapping(value = "/user")
    @ApiOperation(value = "user方法",httpMethod = "GET",notes = "hello方法返回hello",tags = "获得User对象")
    public User getUser(){
        return new User();
    }

    @GetMapping(value = "/user/hello2")
    @ApiOperation("带参数hello方法")
    @ApiImplicitParam(name = "username",value = "用户名",required = true)
    public String hello(String username){
        return "hello"+username;
    }

    /**
     * ApiOperation参数
     * value：方法描述
     * httpMethod:方法请求类型
     * notes:方法注释
     * tags:方法作用（标签）
     * response:返回值的类型对象
     * produces：数据格式
     * protocols:网络协议
     * @return
     */
    @PostMapping(value = "/user/hello3")
    @ApiOperation(value = "带参数hello3方法",protocols = "HTTP",httpMethod = "POST",response = String.class,notes = "带参数hello3方法返回hello",produces = "application/json")
    @ApiImplicitParam(name = "username",value = "用户名",required = true)
    public String hello2(@RequestBody String username){
        return "hello"+username;
    }
}
