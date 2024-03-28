package com.api.music.usecases.artist;

import com.api.music.repository.artist.ArtistRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DeleteArtistUseCaseTest {

  @Mock
  private ArtistRepositoryPort artistRepository;

  @InjectMocks
  private DeleteArtistUseCase deleteArtistUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDeleteArtist() {

    Long artistId = 1L;

    deleteArtistUseCase.deleteArtist(artistId);
    Mockito.verify(artistRepository, Mockito.times(1)).delete(artistId);
  }
}