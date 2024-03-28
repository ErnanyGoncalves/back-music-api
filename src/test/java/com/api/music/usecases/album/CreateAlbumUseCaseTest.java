package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CreateAlbumUseCaseTest {

  @Mock
  private AlbumRepositoryPort albumRepository;

  @InjectMocks
  private CreateAlbumUseCase createAlbumUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCreateAlbum() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);

    createAlbumUseCase.createAlbum(album);

    Mockito.verify(albumRepository, Mockito.times(1)).save(album);
  }
}