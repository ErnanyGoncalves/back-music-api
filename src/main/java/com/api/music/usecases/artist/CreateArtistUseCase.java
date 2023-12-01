package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    public CreateArtistUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void createArtist( Artist artist){
        artistRepository.save(artist);
    }
}
