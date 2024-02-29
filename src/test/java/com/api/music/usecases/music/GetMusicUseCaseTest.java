package com.api.music.usecases.music;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.mappers.MusicMapper;
import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetMusicUseCaseTest {
  @Mock
  private MusicRepositoryPort musicRepository;

  @Mock
  private MusicMapper musicMapper;

  @InjectMocks
  private GetMusicUseCase getMusicUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetMusic() {

    Long id = 1L;
    String title = "Title";
    Integer trackNum = 1;
    Integer duration = 3600;
    AlbumDTO album = new AlbumDTO(1L,"Title","http://example.com/image.jpg",2000,10,3600);
    ArtistDTO artist = new ArtistDTO(1L,"Artist","http://example.com/image.jpg","Country","Genre");

    Music music = new Music();

    MusicDTO musicDTO = new MusicDTO(id,title,trackNum,duration,album,artist);
    Mockito.when(musicRepository.findById(id)).thenReturn(Optional.of(music));
    Mockito.when(musicMapper.toDto(music)).thenReturn(musicDTO);


    MusicDTO result = getMusicUseCase.getMusic(id);


    assertEquals(musicDTO, result);
    Mockito.verify(musicRepository, Mockito.times(1)).findById(id);
    Mockito.verify(musicMapper, Mockito.times(1)).toDto(music);
  }

  @Test
  public void testGetMusicNotFound() {

    Long id = 1L;
    Mockito.when(musicRepository.findById(id)).thenReturn(Optional.empty());


    assertThrows(NoSuchElementException.class, () -> getMusicUseCase.getMusic(id));
    Mockito.verify(musicRepository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(musicMapper);
  }
}