package com.api.music.usecases.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.repository.artist.ArtistRepositoryPort;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetArtistsUseCase {

  private final ArtistRepositoryPort artistRepository;
  private final ArtistMapper artistMapper;


  public List<ArtistDTO> getArtists(List<String> countries, List<String> genres, Integer page,
      Integer pageSize) {
    return artistRepository.findAll(countries, genres, page, pageSize).stream()
        .map(this.artistMapper::toDto).toList();
  }
}
