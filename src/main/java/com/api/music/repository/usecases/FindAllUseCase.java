package com.api.music.repository.usecases;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUseCase {

    private final ArtistRepositoryPort artistRepository;

    public FindAllUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAll(){
        return null;
    }

    public List<Artist> findAll(List<String> originCountries, List<String> genres) {
        return null;
    }

}
