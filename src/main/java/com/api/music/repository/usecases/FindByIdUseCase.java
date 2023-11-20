package com.api.music.repository.usecases;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;


@Service
public class FindByIdUseCase {

    private final ArtistRepositoryPort artistRepository;

    private Long id;

    public FindByIdUseCase(ArtistRepositoryPort artistRepository, Long id) {
        this.artistRepository = artistRepository;
        this.id = id;
    }

    public Artist findById(Long id){
        return null;
    }
}
