package com.api.music.adapters.api;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.models.Music;
import com.api.music.usecases.music.CreateMusicUseCase;
import com.api.music.usecases.music.DeleteMusicUseCase;
import com.api.music.usecases.music.EditMusicUseCase;
import com.api.music.usecases.music.GetMusicUseCase;
import com.api.music.usecases.music.GetMusicsUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
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
class MusicApiImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetMusicsUseCase getMusicsUseCase;

  @MockBean
  private GetMusicUseCase getMusicUseCase;
  @MockBean
  private CreateMusicUseCase createMusicUseCase;
  @MockBean
  private EditMusicUseCase editMusicUseCase;
  @MockBean
  private DeleteMusicUseCase deleteMusicUseCase;

  @Test
  public void testGetMusics() throws Exception {

//    Long id = 1L;
//    String name = "name";
//    String imageUrl = "http://example.com/image.jpg";
//    String originCountry = "country";
//    String genre = "genre";
//    ArtistDTO mockArtist = new ArtistDTO(id, name, imageUrl, originCountry, genre);
//
//    String title = "title";
//    Integer year = 2000;
//    Integer numOfTracks = 1;
//    Integer totalDuration = 3000;
//    AlbumDTO mockAlbum = new AlbumDTO(id, title, imageUrl, year, numOfTracks, totalDuration);
//
//    Integer trackNum = 1;
//    Integer duration = 3000;
//    List<MusicDTO> mockMusics = Collections.singletonList(
//        new MusicDTO(id, title, trackNum, duration, mockAlbum, mockArtist));
//    Mockito.when(getMusicsUseCase.getMusics(Mockito.anyList(), Mockito.anyList(), Mockito.anyInt(),
//            Mockito.anyInt()))
//        .thenReturn(mockMusics);
//
//    mockMvc.perform(get("/musics")
//            .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////        .andExpect(jsonPath("$.data").isArray())
////        .andExpect(jsonPath("$.data[0].title").value(mockMusics.get(0).getTitle()));
//        .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
  }

  @Test
  public void testGetMusic() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    ArtistDTO mockArtist = new ArtistDTO(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;
    Integer numOfTracks = 1;
    Integer totalDuration = 3000;
    AlbumDTO mockAlbum = new AlbumDTO(id, title, imageUrl, year, numOfTracks, totalDuration);

    Integer trackNum = 1;
    Integer duration = 3000;
    MusicDTO mockMusic = new MusicDTO(id, title, trackNum, duration, mockAlbum, mockArtist);

    Mockito.when(getMusicUseCase.getMusic(id))
        .thenReturn(mockMusic);

    mockMvc.perform(get("/musics/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(id.intValue()))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.trackNum").value(trackNum))
        .andExpect(jsonPath("$.duration").value(duration));
    //.andExpect(jsonPath("$.album").value(mockAlbum));    //COMO FAZER O EXPECT DE UM OBJETO?
    //.andExpect(jsonPath("$.artist").value(mockArtist));    //COMO FAZER O EXPECT DE UM OBJETO?
  }

  @Test
  public void testCreateMusic() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    Artist mockArtist = new Artist(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;
    Album mockAlbum = new Album(id, title, imageUrl, year, mockArtist);

    Integer trackNum = 1;
    Integer duration = 3000;
    Music mockMusic = new Music(id, title, trackNum, duration, mockAlbum, mockArtist);

    mockMvc.perform(post("/musics")
            .content(new ObjectMapper().writeValueAsString(mockMusic))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    Mockito.verify(createMusicUseCase).createMusic(mockMusic);
  }

  @Test
  public void testEditMusic() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";
    Artist mockArtist = new Artist(id, name, imageUrl, originCountry, genre);

    String title = "title";
    Integer year = 2000;

    Album mockAlbum = new Album(null, title, imageUrl, year, mockArtist);

    Integer trackNum = 1;
    Integer duration = 3000;
    Music mockMusic = new Music(id, title, trackNum, duration, mockAlbum, mockArtist);

    mockMvc.perform(put("/musics/1")
            .content(new ObjectMapper().writeValueAsString(mockMusic))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(editMusicUseCase).editMusic(id, mockMusic);
  }

  @Test
  public void testDeleteMusic() throws Exception {

    Long id = 1L;

    mockMvc.perform(delete("/musics/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(deleteMusicUseCase).deleteMusic(id);
  }
}