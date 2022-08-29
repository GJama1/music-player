package com.study.myprojects.musicplayer.facade;

import com.study.myprojects.musicplayer.model.dto.ArtistDto;
import com.study.myprojects.musicplayer.model.param.ArtistParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistFacade {


    public Page<ArtistDto> findAll(int page, int size, String sort, String direction) {
        return null;
    }

    public ArtistDto findById(Long id) {
        return null;
    }

    public ArtistDto create(ArtistParam param, MultipartFile image) {
        return null;
    }

    public ArtistDto update(UUID id, ArtistParam param, MultipartFile image) {
        return null;
    }

    public void delete(UUID id) {

    }
}
