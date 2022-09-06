package com.study.myprojects.musicplayer.service;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import org.keycloak.representations.AccessTokenResponse;

public interface AuthService {

    AccessTokenResponse login(LoginParam loginParam);

    String getSessionId();

    AccessTokenResponse refreshToken(RefreshTokenParam refreshTokenParam);

    void logout(String sessionId);

    void createUser(UserEntity user, CharSequence password);

}
