package com.study.myprojects.musicplayer.service;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.enums.Status;
import com.study.myprojects.musicplayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public boolean existsByUsername(String username, Status status) {
        return repository.existsByUsernameAndStatus(username, status);
    }
}
