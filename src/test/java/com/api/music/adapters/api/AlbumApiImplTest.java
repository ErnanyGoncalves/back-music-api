package com.api.music.adapters.api;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.usecases.album.CreateAlbumUseCase;
import com.api.music.usecases.album.DeleteAlbumUseCase;
import com.api.music.usecases.album.EditAlbumUseCase;
import com.api.music.usecases.album.GetAlbumUseCase;
import com.api.music.usecases.album.GetAlbumsUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumApiImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetAlbumsUseCase getAlbumsUseCase;

  @MockBean
  private GetAlbumUseCase getAlbumUseCase;
  @MockBean
  private CreateAlbumUseCase createAlbumUseCase;
  @MockBean
  private EditAlbumUseCase editAlbumUseCase;
  @MockBean
  private DeleteAlbumUseCase deleteAlbumUseCase;

  @Test
  public void testGetAlbums() throws Exception {
//    // CORRIGIR LINK - PORQUE O RETORNO DO WHEN NÃO VEM CORRETAMENTE?
//    Long id = 1L;
//    String name = "name";
//    String imageUrl = "http://example.com/image.jpssssssssg";
//    String originCountry = "country";
//    String genre = "genre";
//    ArtistDTO mockArtist = new ArtistDTO(id, name, imageUrl, originCountry, genre);
//
//    String title = "title";
//    Integer year = 2000;
//    Integer numOfTracks = 0;
//    Integer totalDuration = 0;
//    List<AlbumWithArtistDTO> mockAlbums = Collections.singletonList(
//        new AlbumWithArtistDTO(id, title, imageUrl, year, numOfTracks, totalDuration, mockArtist));
//    Mockito.when(getAlbumsUseCase.getAlbums(Mockito.anyList(), Mockito.anyList(), Mockito.anyInt(),
//            Mockito.anyInt()))
//        .thenReturn(mockAlbums);
//
//    mockMvc.perform(get("/albums")
//            .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////        .andExpect(jsonPath("$.data").isArray())
////        .andExpect(jsonPath("$.data[0].title").value(mockAlbums.get(0).getTitle()));
//        .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
  }

  @Test
  public void testGetAlbum() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    ArtistDTO mockArtist = new ArtistDTO(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;
    Integer numOfTracks = 0;
    Integer totalDuration = 0;
    AlbumWithArtistDTO mockAlbum = new AlbumWithArtistDTO(id, title, imageUrl, year, numOfTracks,
        totalDuration, mockArtist);

    Mockito.when(getAlbumUseCase.getAlbum(id))
        .thenReturn(mockAlbum);

    mockMvc.perform(get("/albums/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(id.intValue()))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.imageUrl").value(imageUrl))
        .andExpect(jsonPath("$.year").value(year))
        .andExpect(jsonPath("$.numOfTracks").value(numOfTracks))
        .andExpect(jsonPath("$.totalDuration").value(totalDuration));
    //.andExpect(jsonPath("$.artist").value(mockArtist));    //COMO FAZER O EXPECT DE UM OBJETO?
  }

  @Test
  public void testCreateArtist() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    Artist mockArtist = new Artist(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;
    Album mockAlbum = new Album(null, title, imageUrl, year, mockArtist);

    mockMvc.perform(post("/albums")
            .content(new ObjectMapper().writeValueAsString(mockAlbum))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    Mockito.verify(createAlbumUseCase).createAlbum(mockAlbum);
  }

  @Test
  public void testEditArtist() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    Artist mockArtist = new Artist(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;
    Album mockAlbum = new Album(null, title, imageUrl, year, mockArtist);

    mockMvc.perform(put("/albums/1")
            .content(new ObjectMapper().writeValueAsString(mockAlbum))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(editAlbumUseCase).editAlbum(id, mockAlbum);
  }

  @Test
  public void testDeleteAlbum() throws Exception {

    Long id = 1L;

    mockMvc.perform(delete("/albums/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(deleteAlbumUseCase).deleteAlbum(id);
  }
}