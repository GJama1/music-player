package com.study.myprojects.musicplayer.repository;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

//    @Query("""
//            select case when exists (
//                select u from UserEntity u
//                where u.username = :username
//                and u.status = :status
//            ) then true else false end
//            from UserEntity u
//            """)
    Boolean existsByUsernameAndStatus(
            @Param("username") String username,
            @Param("status") Status status
    );

}
