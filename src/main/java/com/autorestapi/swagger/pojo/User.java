package com.autorestapi.swagger.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @Since: Created  in 14:40 2020/12/1
 * @Author: Ljx
 * @Decription:
 */
@ApiModel("用户实体类")
@Data
public class User {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
