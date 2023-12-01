package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetArtistUseCase {

    private final ArtistRepositoryPort artistRepository;


    public GetArtistUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist getArtist(Long id){
        final var artist = artistRepository.findById(id);

        return artist.orElseThrow();
    }
}
