package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class EditArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    public EditArtistUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void editArtist(Long id, Artist newArtistData){
        artistRepository.edit(id,newArtistData);
    }
}
