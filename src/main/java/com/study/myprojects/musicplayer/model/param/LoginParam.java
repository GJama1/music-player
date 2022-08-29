package com.study.myprojects.musicplayer.model.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginParam {

    @NotBlank(message = "Username is required")
    private String username;

    private CharSequence password;

}
