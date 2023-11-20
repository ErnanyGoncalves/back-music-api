package com.api.music.usecases.Artist;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class EditArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    private Long id;
    private Artist newArtistData;
    public EditArtistUseCase(ArtistRepositoryPort artistRepository, Long id, Artist newArtistData) {
        this.artistRepository = artistRepository;
        this.id = id;
        this.newArtistData= newArtistData;
    }

    public void editArtist(Long id, Artist newArtistData){
        artistRepository.edit(id,newArtistData);
    }
}
