package com.study.myprojects.musicplayer.model.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenParam {

    @NotBlank(message = "Refresh token is required.")
    private String refreshToken;

}
