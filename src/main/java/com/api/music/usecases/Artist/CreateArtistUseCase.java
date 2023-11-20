package com.api.music.usecases.Artist;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    private Artist artist;
    public CreateArtistUseCase(ArtistRepositoryPort artistRepository, Artist artist) {
        this.artistRepository = artistRepository;
        this.artist= artist;
    }

    public void createArtist( Artist artist){
        artistRepository.save(artist);
    }
}
