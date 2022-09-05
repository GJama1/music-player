package com.study.myprojects.musicplayer.model.param;

import com.study.myprojects.musicplayer.annotation.Email;
import com.study.myprojects.musicplayer.annotation.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpParam {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    private String username;

    @Email
    private String email;

    @Password
    private CharSequence password;

}
