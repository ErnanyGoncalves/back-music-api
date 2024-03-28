package com.api.music.usecases.music;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EditMusicUseCaseTest {

  @Mock
  private MusicRepositoryPort musicRepository;

  @InjectMocks
  private EditMusicUseCase editMusicUseCase;


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void editMusic_Successfully() {

    Long id = 1L;
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);
    Music newMusicData = new Music(id, "Marry the Night", 0, 0, album, artist);

    Mockito.doNothing().when(musicRepository).edit(id, newMusicData);

    editMusicUseCase.editMusic(id, newMusicData);

    Mockito.verify(musicRepository, Mockito.times(1)).edit(id, newMusicData);
  }

  @Test
  void editMusic_WhenRepositoryThrowsException() {

    Long id = 1L;
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);
    Music newMusicData = new Music(id, "Marry the Night", 0, 0, album, artist);

    Mockito.doThrow(new RuntimeException("Repository exception")).when(musicRepository)
        .edit(id, newMusicData);

    assertThrows(RuntimeException.class, () -> editMusicUseCase.editMusic(id, newMusicData));
  }
}