package com.study.myprojects.musicplayer.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(Environment env) {

        return MinioClient.builder()
                .endpoint(
                        Objects.requireNonNull(env.getProperty("S3_INTERNAL_HOST")),
                        Integer.parseInt(Objects.requireNonNull(env.getProperty("S3_INTERNAL_PORT"))),
                        false)
                .credentials(
                        Objects.requireNonNull(env.getProperty("S3_ACCESS_KEY")),
                        Objects.requireNonNull(env.getProperty("S3_SECRET_KEY"))
                )
                .build();

    }

}
