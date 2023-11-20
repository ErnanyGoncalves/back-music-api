package com.api.music.repository.usecases;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteUseCase {
    private final ArtistRepositoryPort artistRepository;

    private Long id;

    public DeleteUseCase(ArtistRepositoryPort artistRepository, Long id) {
        this.artistRepository = artistRepository;
        this.id =id;
    }

    public void delete(Long id){

    }
}
