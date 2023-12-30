package com.api.music.usecases.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetArtistsUseCase {
    private final ArtistRepositoryPort artistRepository;
    private final ArtistMapper artistMapper;
    public GetArtistsUseCase(ArtistRepositoryPort artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDTO> getArtists(Integer page, Integer pageSize){
        return artistRepository.findAll( page,  pageSize).stream().map(this.artistMapper::toDto).toList();
    }

    public List<ArtistDTO> getArtists(List<String> countries,List<String> genres,Integer page, Integer pageSize){
        return artistRepository.findAll(countries,genres,page,pageSize).stream().map(this.artistMapper::toDto).toList();
    }
}
