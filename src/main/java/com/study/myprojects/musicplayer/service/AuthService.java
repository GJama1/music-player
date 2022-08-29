package com.study.myprojects.musicplayer.service;

import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import org.keycloak.representations.AccessTokenResponse;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AuthService {

    AccessTokenResponse login(LoginParam loginParam);

    AccessTokenResponse refreshToken(RefreshTokenParam refreshTokenParam);

    void logout(String sessionId);

}
