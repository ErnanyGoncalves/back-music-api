package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateArtistUseCase {

  private final ArtistRepositoryPort artistRepository;

  public void createArtist(Artist artist) {
    artistRepository.save(artist);
  }
}
