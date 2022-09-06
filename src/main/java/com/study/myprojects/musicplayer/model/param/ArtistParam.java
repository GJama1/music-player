package com.study.myprojects.musicplayer.model.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class ArtistParam {

    @NotBlank(message = "Name is required.")
    private String name;

    private LocalDate creationDate;

}
