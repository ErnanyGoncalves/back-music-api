package com.api.music.repository.usecases;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class EditUseCase {

    private final ArtistRepositoryPort artistRepository;

    private Long id;
    private Artist newArtistData;

    public EditUseCase(ArtistRepositoryPort artistRepository, Long id, Artist newArtistData) {
        this.artistRepository = artistRepository;
        this.newArtistData = newArtistData;
    }

    public void edit(Long id,Artist newArtistData){

    }
}
