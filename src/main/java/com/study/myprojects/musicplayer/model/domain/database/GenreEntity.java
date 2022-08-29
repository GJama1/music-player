package com.study.myprojects.musicplayer.model.domain.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity extends SuperEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
