package com.study.myprojects.musicplayer.facade;

import com.study.myprojects.musicplayer.model.dto.TokenDto;
import com.study.myprojects.musicplayer.model.mapper.TokenMapper;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import com.study.myprojects.musicplayer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;

    public TokenDto login(LoginParam param) {
        return TokenMapper.toTokenDto(authService.login(param));
    }

    public void logout() {
        authService.logout(authService.getSessionId());
    }

    public TokenDto refresh(RefreshTokenParam param) {
        return TokenMapper.toTokenDto(authService.refreshToken(param));
    }

}
