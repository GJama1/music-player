package com.study.myprojects.musicplayer.model.domain.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "songs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity extends SuperEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;

}
