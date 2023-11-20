package com.api.music.usecases.Artist;

import com.api.music.models.Artist;
import com.api.music.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetArtistsUseCase {
    private final ArtistRepositoryPort artistRepository;
    private List<String> originCountries = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    public GetArtistsUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public GetArtistsUseCase(ArtistRepositoryPort artistRepository, List<String> originCountries, List<String> genres) {
        this.artistRepository = artistRepository;
        this.originCountries = originCountries;
        this.genres = genres;
    }

    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    public List<Artist> getArtists(List<String> countries,List<String> genres){
        return artistRepository.findAll(countries,genres);
    }
}
