package com.study.myprojects.musicplayer.repository;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByUsernameAndStatus(String username, Status status);

}
