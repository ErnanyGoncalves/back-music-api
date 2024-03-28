package com.api.music.usecases.album;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.repository.album.AlbumRepositoryPort;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetAlbumsUseCaseTest {

  @Mock
  private AlbumRepositoryPort albumRepository;

  @Mock
  private AlbumWithArtistMapper albumMapper;

  @InjectMocks
  private GetAlbumsUseCase getAlbumsUseCase;


  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAlbums() {
    List<Integer> years = Arrays.asList(2020, 2021);
    List<String> artists = Arrays.asList("Artist1", "Artist2");
    Integer page = 1;
    Integer pageSize = 10;

    Artist artist = new Artist(1L, "Artist1", "image1.jpg", "USA", "Rock");
    ArtistDTO artistDto = new ArtistDTO(1L, "Artist1", "image1.jpg", "USA", "Rock");

    List<Album> albumsFromRepo = Arrays.asList(
        new Album(1L, "Album 1", "image1.jpg", 2020, artist),
        new Album(2L, "Album 2", "image2.jpg", 2021, artist)
    );
    List<AlbumWithArtistDTO> expectedAlbums = Arrays.asList(
        new AlbumWithArtistDTO(1L, "Album 1", "image1.jpg", 2020, 0, 0, artistDto),
        new AlbumWithArtistDTO(2L, "Album 2", "image2.jpg", 2021, 0, 0, artistDto)
    );

    Mockito.when(albumRepository.findAll(years, artists, page, pageSize))
        .thenReturn(albumsFromRepo);
    Mockito.when(albumMapper.toDto(Mockito.any(Album.class))).thenAnswer(invocation -> {
      AlbumWithArtistDTO album = invocation.getArgument(0);
      return new AlbumWithArtistDTO(album.getId(), album.getTitle(), album.getImageUrl(),
          album.getYear(), album.getNumOfTracks(), album.getTotalDuration(), album.getArtist());
    });

    List<AlbumWithArtistDTO> actualAlbums = getAlbumsUseCase.getAlbums(years, artists, page,
        pageSize);
  }
}