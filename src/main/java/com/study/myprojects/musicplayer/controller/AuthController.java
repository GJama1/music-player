package com.study.myprojects.musicplayer.controller;

import com.study.myprojects.musicplayer.facade.AuthFacade;
import com.study.myprojects.musicplayer.model.dto.TokenDto;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import com.study.myprojects.musicplayer.model.param.SignUpParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> logic(@RequestBody @Valid LoginParam param){
        return ResponseEntity.status(HttpStatus.OK).body(authFacade.login(param));
    }

    @PostMapping("/logout")
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<Void> logout(){
        authFacade.logout();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@RequestBody @Valid RefreshTokenParam param){
        return ResponseEntity.status(HttpStatus.OK).body(authFacade.refresh(param));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpParam param) {
        authFacade.signUp(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
