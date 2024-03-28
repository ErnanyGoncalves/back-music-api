package com.api.music.usecases.album;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EditAlbumUseCaseTest {

  @Mock
  private AlbumRepositoryPort albumRepository;

  @InjectMocks
  private EditAlbumUseCase editAlbumUseCase;


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void editAlbum_Successfully() {

    Long id = 1L;
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album newAlbumData = new Album(id, "Born This Way", null, 2011, artist);
    Mockito.doNothing().when(albumRepository).edit(id, newAlbumData);

    editAlbumUseCase.editAlbum(id, newAlbumData);

    Mockito.verify(albumRepository, Mockito.times(1)).edit(id, newAlbumData);
  }

  @Test
  void editAlbum_WhenRepositoryThrowsException() {

    Long id = 1L;
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album newAlbumData = new Album(id, "Born This Way", null, 2011, artist);

    Mockito.doThrow(new RuntimeException("Repository exception")).when(albumRepository)
        .edit(id, newAlbumData);

    assertThrows(RuntimeException.class, () -> editAlbumUseCase.editAlbum(id, newAlbumData));
  }
}