package com.api.music.usecases.Artist;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    private Long id;

    public GetArtistUseCase(ArtistRepositoryPort artistRepository, Long id) {
        this.artistRepository = artistRepository;
        this.id = id;
    }

    public Artist getArtist(Long id){
        return artistRepository.findById(id);
    }
}
