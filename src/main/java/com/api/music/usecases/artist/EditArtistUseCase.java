package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditArtistUseCase {

    private final ArtistRepositoryPort artistRepository;

    public void editArtist(Long id, Artist newArtistData) {
        artistRepository.edit(id, newArtistData);
    }
}
