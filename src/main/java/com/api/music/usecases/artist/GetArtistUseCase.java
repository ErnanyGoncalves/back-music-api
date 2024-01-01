package com.api.music.usecases.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.repository.artist.ArtistRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetArtistUseCase {

    private final ArtistRepositoryPort artistRepository;
    private final ArtistMapper artistMapper;

    public ArtistDTO getArtist(Long id) {
        return artistRepository.findById(id).map(this.artistMapper::toDto).orElseThrow();
    }
}
