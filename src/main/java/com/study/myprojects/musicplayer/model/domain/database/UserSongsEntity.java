package com.study.myprojects.musicplayer.model.domain.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_songs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSongsEntity extends SuperEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private SongEntity song;

    @Column(name = "is_favorite", nullable = false)
    private Boolean isFavorite;

}

