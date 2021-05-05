package ru.romankuznetsov.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class JwtRequest {
    @ApiModelProperty(notes = "Username", required = true, position = 1, example = "bob1")
    private String username;
    @ApiModelProperty(notes = "Password", required = true, position = 2, example = "111")
    private String password;

}
