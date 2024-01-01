package com.api.music.usecases.artist;

import com.api.music.repository.artist.ArtistRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteArtistUseCase {

    private final ArtistRepositoryPort artistRepository;

    public void deleteArtist(Long id) {
        artistRepository.delete(id);
    }
}
