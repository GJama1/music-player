package com.study.myprojects.musicplayer.controller;

import com.study.myprojects.musicplayer.facade.ArtistFacade;
import com.study.myprojects.musicplayer.model.dto.ArtistDto;
import com.study.myprojects.musicplayer.model.enums.Roles;
import com.study.myprojects.musicplayer.model.param.ArtistParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistFacade artistFacade;

    @GetMapping
    @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<Page<ArtistDto>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "ASC") String direction
    ){
        return ResponseEntity.ok().body(artistFacade.findAll(page, size, sort, direction));
    }

    @GetMapping("/{id}")
    @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<ArtistDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(artistFacade.findById(id));
    }

    @PostMapping
    @Secured({Roles.ROLE_ADMIN})
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<ArtistDto> create(@RequestBody @Valid ArtistParam param, @RequestParam(required = false) MultipartFile image){
        return ResponseEntity.ok().body(artistFacade.create(param, image));
    }

    @PutMapping("/{id}")
    @Secured({Roles.ROLE_ADMIN})
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<ArtistDto> update(
            @PathVariable("id") UUID id,
            @RequestBody ArtistParam param,
            @RequestParam(required = false) MultipartFile image
    ){
        return ResponseEntity.ok().body(artistFacade.update(id, param, image));
    }

    @DeleteMapping("/{id}")
    @Secured({Roles.ROLE_ADMIN})
    @Operation(
            security = @SecurityRequirement(name = "bearer-token")
    )
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        artistFacade.delete(id);
        return ResponseEntity.noContent().build();
    }

}
