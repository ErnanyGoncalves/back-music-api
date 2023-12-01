package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetArtistsUseCase {
    private final ArtistRepositoryPort artistRepository;
    public GetArtistsUseCase(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getArtists(int page, int pageSize){
        return artistRepository.findAll( page,  pageSize);
    }

    public List<Artist> getArtists(List<String> countries,List<String> genres,int page, int pageSize){
        return artistRepository.findAll(countries,genres,page,pageSize);
    }
}
