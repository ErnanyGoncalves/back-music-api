package com.api.music.usecases.Artist;

import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    private Long id;

    public DeleteArtistUseCase(ArtistRepositoryPort artistRepository, Long id) {
        this.artistRepository = artistRepository;
        this.id = id;
    }

    public void deleteArtist(Long id) {
        artistRepository.delete(id);
    }
}
