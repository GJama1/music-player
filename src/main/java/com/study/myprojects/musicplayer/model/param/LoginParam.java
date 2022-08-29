package com.study.myprojects.musicplayer.model.param;

import com.study.myprojects.musicplayer.annotation.Password;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginParam {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    private String username;

    @Password
    private CharSequence password;

    private boolean rememberMe = false;

}
