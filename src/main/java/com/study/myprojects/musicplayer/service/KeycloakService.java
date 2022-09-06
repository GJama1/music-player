package com.study.myprojects.musicplayer.service;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.param.LoginParam;
import com.study.myprojects.musicplayer.model.param.RefreshTokenParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService implements AuthService {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final Keycloak keycloakClient;
    private final AuthzClient authzClient;

    private static CredentialRepresentation createPasswordCredentials(CharSequence password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();

        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password.toString());

        return passwordCredentials;
    }

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
    public String getSessionId() {

        JwtAuthenticationToken jwt = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String sessionId = (String) jwt.getTokenAttributes().getOrDefault("session_state", "NOT");

        if (sessionId.equals("NOT"))
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't find sessionId in token.");
        return sessionId;

    }

    @Override
    public AccessTokenResponse refreshToken(RefreshTokenParam refreshTokenParam) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", Objects.requireNonNull(env.getProperty("AUTH_CLIENT_ID")));
        body.add("client_secret", Objects.requireNonNull(env.getProperty("AUTH_CLIENT_SECRET")));
        body.add("refresh_token", refreshTokenParam.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(Objects.requireNonNull(env.getProperty("AUTH_TOKEN_ENDPOINT")), request, AccessTokenResponse.class);
    }

    @Override
    public void logout(String sessionId) {
        keycloakClient.realm(Objects.requireNonNull(env.getProperty("AUTH_REALM"))).deleteSession(sessionId);
    }

    @Override
    public void createUser(UserEntity user, CharSequence password) {

        UsersResource usersResource = keycloakClient.realm(Objects.requireNonNull(env.getProperty("AUTH_REALM"))).users();
        UserRepresentation kcUser = new UserRepresentation();

        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(createPasswordCredentials(password)));
        kcUser.setEmail(user.getEmail());
        kcUser.setAttributes(Map.of("database_id", List.of(user.getId().toString())));
        kcUser.setEnabled(true);

        Response response = usersResource.create(kcUser);
        if (!Objects.equals(response.getStatus(), HttpStatus.CREATED.value())) {
            log.error("Error creating user in Keycloak. Status: {}, reason: {}", response.getStatus(), response.getStatusInfo().getReasonPhrase());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error happened while creating user.");
        }

    }

}
