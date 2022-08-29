package com.study.myprojects.musicplayer.model.mapper;

import com.study.myprojects.musicplayer.model.dto.TokenDto;
import org.keycloak.representations.AccessTokenResponse;

public class TokenMapper {

    public static TokenDto toTokenDto(AccessTokenResponse response) {
        return new TokenDto(
                response.getToken(),
                response.getExpiresIn(),
                response.getRefreshToken(),
                response.getRefreshExpiresIn()
        );
    }

}
