package com.study.myprojects.musicplayer.service;

import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KeycloakService implements AuthService {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final Keycloak keycloakClient;
    private final AuthzClient authzClient;

    @Override
    public AccessTokenResponse login(LoginParam loginParam) {

        String scope = loginParam.isRememberMe() ? "offline_access" : null;

        return authzClient.authorization(
                loginParam.getUsername(),
                loginParam.getPassword().toString(),
                scope
        ).authorize();

    }

    @Override
    public AccessTokenResponse refreshToken(RefreshTokenParam refreshTokenParam) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", env.getProperty("${AUTH_CLIENT_ID}"));
        body.add("client_secret", env.getProperty("${AUTH_CLIENT_SECRET}"));
        body.add("refresh_token", refreshTokenParam.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(env.getProperty("${AUTH_TOKEN_ENDPOINT}"), request, AccessTokenResponse.class);
    }

    @Override
    public void logout(String sessionId) {
        keycloakClient.realm(env.getProperty("${AUTH_REALM}")).deleteSession(sessionId);
    }



}
