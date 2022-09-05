package com.study.myprojects.musicplayer.facade;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.dto.TokenDto;
import com.study.myprojects.musicplayer.model.enums.Status;
import com.study.myprojects.musicplayer.model.mapper.TokenMapper;
import com.study.myprojects.musicplayer.model.mapper.UserMapper;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import com.study.myprojects.musicplayer.model.param.SignUpParam;
import com.study.myprojects.musicplayer.service.AuthService;
import com.study.myprojects.musicplayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;
    private final UserService userService;

    public TokenDto login(LoginParam param) {
        return TokenMapper.toTokenDto(authService.login(param));
    }

    public void logout() {
        authService.logout(authService.getSessionId());
    }

    public TokenDto refresh(RefreshTokenParam param) {
        return TokenMapper.toTokenDto(authService.refreshToken(param));
    }

    @Transactional
    public void signUp(SignUpParam param) {

        if (userService.existsByUsername(param.getUsername(), Status.ACTIVE)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username already exists.");
        }

        UserEntity user = userService.save(UserMapper.toUserEntity(param));
        authService.createUser(user, param.getPassword());

    }

}
