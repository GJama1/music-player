package com.study.myprojects.musicplayer.facade;

import com.study.myprojects.musicplayer.model.dto.TokenDto;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import org.springframework.stereotype.Service;

@Service
public class AuthFacade {

    public TokenDto login(LoginParam param) {
        return null;
    }

    public void logout() {

    }

    public TokenDto refresh(RefreshTokenParam param) {
        return null;
    }

}
