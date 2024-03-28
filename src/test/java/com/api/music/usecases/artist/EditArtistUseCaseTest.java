package com.api.music.usecases.artist;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EditArtistUseCaseTest {

  @Mock
  private ArtistRepositoryPort artistRepository;

  @InjectMocks
  private EditArtistUseCase editArtistUseCase;


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  void editArtist_Successfully() {

    Long id = 1L;
    Artist newArtistData = new Artist(id, "Lady Gaga", null, "United States", "Pop");

    Mockito.doNothing().when(artistRepository).edit(id, newArtistData);

    editArtistUseCase.editArtist(id, newArtistData);

    Mockito.verify(artistRepository, Mockito.times(1)).edit(id, newArtistData);
  }

  @Test
  void editArtist_WhenRepositoryThrowsException() {

    Long id = 1L;
    Artist newArtistData = new Artist(id, "Lady Gaga", null, "United States", "Pop");

    Mockito.doThrow(new RuntimeException("Repository exception")).when(artistRepository)
        .edit(id, newArtistData);

    assertThrows(RuntimeException.class, () -> editArtistUseCase.editArtist(id, newArtistData));
  }

}