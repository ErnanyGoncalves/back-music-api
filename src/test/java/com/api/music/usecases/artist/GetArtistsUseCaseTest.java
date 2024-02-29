package com.api.music.usecases.artist;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepositoryPort;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetArtistsUseCaseTest {
  @Mock
  private ArtistRepositoryPort artistRepository;

  @Mock
  private ArtistMapper artistMapper;

  @InjectMocks
  private GetArtistsUseCase getArtistsUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetArtists() {


    List<String> countries = Arrays.asList("USA", "UK");
    List<String> genres = Arrays.asList("Rock", "Pop");
    Integer page = 1;
    Integer pageSize = 10;


    List<Artist> artistsFromRepo = Arrays.asList(
        new Artist(1L, "Artist1", "image1.jpg", "USA", "Rock"),
        new Artist(2L, "Artist2", "image2.jpg", "UK", "Pop")
    );
    List<ArtistDTO> expectedArtists = Arrays.asList(
        new ArtistDTO(1L, "Artist1", "image1.jpg", "USA", "Rock"),
        new ArtistDTO(2L, "Artist2", "image2.jpg", "UK", "Pop")
    );
    Mockito.when(artistRepository.findAll(countries, genres, page, pageSize)).thenReturn(artistsFromRepo);
    Mockito.when(artistMapper.toDto(Mockito.any(Artist.class))).thenAnswer(invocation -> {
      Artist artist = invocation.getArgument(0);
      return new ArtistDTO(artist.getId(), artist.getName(), artist.getImageUrl(), artist.getOriginCountry(), artist.getGenre());
    });


    List<ArtistDTO> actualArtists = getArtistsUseCase.getArtists(countries, genres, page, pageSize);

    assertEquals(expectedArtists, actualArtists);
    Mockito.verify(artistRepository, Mockito.times(1)).findAll(countries, genres, page, pageSize);
    Mockito.verify(artistMapper, Mockito.times(artistsFromRepo.size())).toDto(Mockito.any(Artist.class));
  }

}