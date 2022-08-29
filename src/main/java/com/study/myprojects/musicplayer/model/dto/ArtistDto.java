package com.study.myprojects.musicplayer.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {

    private UUID id;

    private String name;

    private LocalDate creationDate;

    private String image;

}
