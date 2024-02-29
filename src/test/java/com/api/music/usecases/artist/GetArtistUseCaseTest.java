package com.api.music.usecases.artist;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import com.api.music.usecases.artist.GetArtistUseCase;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetArtistUseCaseTest {


  @Mock
  private ArtistRepositoryPort artistRepository;

  @Mock
  private ArtistMapper artistMapper;

  @InjectMocks
  private GetArtistUseCase getArtistUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetArtist() {
    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "Country";
    String genre = "Genre";

    Artist artist = new Artist();

    ArtistDTO artistDTO = new ArtistDTO(id, name, imageUrl, originCountry,genre);

    Mockito.when(artistRepository.findById(id)).thenReturn(Optional.of(artist));
    Mockito.when(artistMapper.toDto(artist)).thenReturn(artistDTO);

    ArtistDTO result = getArtistUseCase.getArtist(id);


    assertEquals(artistDTO, result);
    Mockito.verify(artistRepository , Mockito.times(1)).findById(id);
    Mockito.verify(artistMapper, Mockito.times(1)).toDto(artist);



  }

  @Test
  public void testGetArtistNotFound() {


    Long id = 1L;
    Mockito.when(artistRepository.findById(id)).thenReturn(Optional.empty());


    assertThrows(NoSuchElementException.class, () -> getArtistUseCase.getArtist(id));
    Mockito.verify(artistRepository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(artistMapper);

  }
}