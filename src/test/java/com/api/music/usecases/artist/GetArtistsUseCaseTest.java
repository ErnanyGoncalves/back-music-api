package com.api.music.usecases.artist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Artist;
import com.api.music.models.Navigation;
import com.api.music.models.Pagination;
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
    Integer page = 0;
    Integer pageSize = 10;



   Artist artist1 =  new Artist(1L, "Artist1", "image1.jpg", "USA", "Rock");
       Artist artist2 = new Artist(2L, "Artist2", "image2.jpg", "UK", "Pop");
    ArtistDTO artistDTO1 = new ArtistDTO(1L, "Artist1", "image1.jpg", "USA", "Rock");
    ArtistDTO artistDTO2 = new ArtistDTO(2L, "Artist2", "image2.jpg", "UK", "Pop");

        List<Artist> artistsFromRepo = Arrays.asList(
        artist1,artist2
    );
    List<ArtistDTO> expectedArtists = Arrays.asList(
        artistDTO1,artistDTO2
    );

    when(artistRepository.findAll(countries, genres, page, pageSize)).thenReturn(artistsFromRepo);
    when(artistRepository.count(countries,genres)).thenReturn(Long.valueOf(artistsFromRepo.size()));
    when(artistMapper.toDto(artist1)).thenReturn(artistDTO1);
    when(artistMapper.toDto(artist2)).thenReturn(artistDTO2);

    GetArtistsUseCase getArtistsUseCase = new GetArtistsUseCase(artistRepository, artistMapper);
    ResponseListDTO<ArtistDTO> actualResponse = getArtistsUseCase.getArtists(countries, genres,
        page, pageSize);

    assertEquals(expectedArtists, actualResponse.getListOfData());

    Pagination pagination = actualResponse.getPagination();
    assertEquals(1, pagination.getCurrentPage());
    assertEquals(10, pagination.getItemsPerPage());
    assertEquals(2, pagination.getTotalItems());
    assertEquals(1, pagination.getTotalPages());
    Navigation navigation = pagination.getNavigationPaths();
    assertEquals(null, navigation.getPreviousPage());
    assertEquals(null, navigation.getNextPage());

    Mockito.verify(artistRepository, Mockito.times(1)).findAll(countries, genres, page, pageSize);
    Mockito.verify(artistMapper, Mockito.times(artistsFromRepo.size()))
        .toDto(Mockito.any(Artist.class));
  }

}