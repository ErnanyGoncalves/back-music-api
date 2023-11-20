package com.api.music.repository.usecases;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class SaveUseCase {

    private final ArtistRepositoryPort artistRepository;

    private Artist artist;

    public SaveUseCase(ArtistRepositoryPort artistRepository, Artist artist) {
        this.artistRepository = artistRepository;
        this.artist = artist;
    }

    public void save(Artist artist){

    }
}
