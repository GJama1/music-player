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
        String minioHost = Objects.requireNonNull(env.getProperty("S3_INTERNAL_HOST"));
        int minioPort = Integer.parseInt(Objects.requireNonNull(env.getProperty("S3_INTERNAL_PORT")));
        String minioAccessKey = Objects.requireNonNull(env.getProperty("S3_ACCESS_KEY"));
        String minioSecretKey = Objects.requireNonNull(env.getProperty("S3_SECRET_KEY"));

        return MinioClient.builder()
                .endpoint(minioHost, minioPort, false)
                .credentials(minioAccessKey, minioSecretKey)
                .build();
    }

}
