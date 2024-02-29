package com.api.music.usecases.album;

import static org.junit.jupiter.api.Assertions.*;


import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetAlbumUseCaseTest {

  @Mock
  private AlbumRepositoryPort albumRepository;

  @Mock
  private AlbumWithArtistMapper albumMapper;

  @InjectMocks
  private GetAlbumUseCase getAlbumUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAlbum() {
    Long id = 1L;
    String title = "Title";
    String imageUrl = "http://example.com/image.jpg";
    Integer year = 2022;
    Integer numOfTracks = 10;
    Integer totalDuration = 3600;
    ArtistDTO artist = new ArtistDTO(1L,"Artist","http://example.com/image.jpg","Country","Genre");


    Album album = new Album();

    AlbumWithArtistDTO albumDTO = new AlbumWithArtistDTO(id, title, imageUrl, year,numOfTracks,totalDuration,artist);

    Mockito.when(albumRepository.findById(id)).thenReturn(Optional.of(album));
    Mockito.when(albumMapper.toDto(album)).thenReturn(albumDTO);

    AlbumWithArtistDTO result = getAlbumUseCase.getAlbum(id);


    assertEquals(albumDTO, result);
    Mockito.verify(albumRepository , Mockito.times(1)).findById(id);
    Mockito.verify(albumMapper, Mockito.times(1)).toDto(album);
  }

  @Test
  public void testGetAlbumNotFound() {


    Long id = 1L;
    Mockito.when(albumRepository.findById(id)).thenReturn(Optional.empty());


    assertThrows(NoSuchElementException.class, () -> getAlbumUseCase.getAlbum(id));
    Mockito.verify(albumRepository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(albumMapper);

  }
}