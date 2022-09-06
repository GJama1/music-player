package com.study.myprojects.musicplayer.model.mapper;

import com.study.myprojects.musicplayer.model.domain.database.UserEntity;
import com.study.myprojects.musicplayer.model.enums.Status;
import com.study.myprojects.musicplayer.model.param.SignUpParam;

public class UserMapper {

    public static UserEntity toUserEntity(SignUpParam param) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(param.getUsername());
        userEntity.setEmail(param.getEmail());
        userEntity.setStatus(Status.ACTIVE);

        return userEntity;
    }

}
