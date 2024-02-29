package com.api.music.usecases.music;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.models.Album;
import com.api.music.models.Music;
import com.api.music.models.Artist;
import com.api.music.repository.album.AlbumRepositoryPort;
import com.api.music.repository.music.MusicRepositoryPort;
import com.api.music.usecases.music.CreateMusicUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

class CreateMusicUseCaseTest {


  @Mock
  private MusicRepositoryPort musicRepository;

  @Mock
  private AlbumRepositoryPort albumRepository;

  @InjectMocks
  private CreateMusicUseCase createMusicUseCase;

  private Artist artist;
  private Album album;
  private Music music;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    album = new Album(1L, "Born This Way", null, 2011, artist);
    music = new Music(1L, "Marry the Night", 0, 0, album, artist);
  }

  @Test
  public void testCreateMusic() {

    Mockito.doNothing().when(albumRepository).save(album);

    createMusicUseCase.createMusic(music);

    Mockito.verify(albumRepository, Mockito.times(1)).save(album);
    Mockito.verify(musicRepository, Mockito.times(1)).save(music);

    assertEquals(1,album.getNumOfTracks() );
    assertEquals(music.getDuration(), album.getTotalDuration());
  }

  @Test
  public void testCreateMusicDuplicateTrackNumber() {

    Mockito.doThrow(DataIntegrityViolationException.class).when(albumRepository).save(Mockito.any(Album.class));

    assertThrows(DataIntegrityViolationException.class, () -> createMusicUseCase.createMusic(music));
  }
}