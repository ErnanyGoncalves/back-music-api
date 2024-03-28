package com.api.music.usecases.artist;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CreateArtistUseCaseTest {

  @Mock
  private ArtistRepositoryPort artistRepository;

  @InjectMocks
  private CreateArtistUseCase createArtistUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCreateArtist() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");

    createArtistUseCase.createArtist(artist);

    Mockito.verify(artistRepository, Mockito.times(1)).save(artist);
  }
}