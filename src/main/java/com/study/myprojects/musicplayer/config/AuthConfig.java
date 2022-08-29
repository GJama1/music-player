package com.study.myprojects.musicplayer.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class AuthConfig {

    @Bean
    public Keycloak keycloak(Environment env) {

        return KeycloakBuilder.builder()
                .serverUrl(
                        String.format(
                                "%s:%s",
                                Objects.requireNonNull(env.getProperty("AUTH_HOST")),
                                Objects.requireNonNull(env.getProperty("AUTH_PORT"))
                        )
                )
                .realm(Objects.requireNonNull(env.getProperty("AUTH_REALM")))
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(Objects.requireNonNull(env.getProperty("AUTH_CLIENT_ID")))
                .clientSecret(Objects.requireNonNull(env.getProperty("AUTH_CLIENT_SECRET")))
                .username(Objects.requireNonNull(env.getProperty("AUTH_ADMIN_USERNAME")))
                .password(Objects.requireNonNull(env.getProperty("AUTH_ADMIN_PASSWORD")))
                .resteasyClient(resteasyClient())
                .build();

    }

    private ResteasyClient resteasyClient() {

        return new ResteasyClientBuilder()
                .connectionPoolSize(10)
                .build();

    }

}
