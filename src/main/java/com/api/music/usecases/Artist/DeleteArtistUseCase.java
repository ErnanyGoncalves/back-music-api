package com.api.music.usecases.Artist;

import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteArtistUseCase {

    private final ArtistRepositoryPort artistRepository;

    public DeleteArtistUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void deleteArtist(Long id) {
        artistRepository.delete(id);
    }
}
