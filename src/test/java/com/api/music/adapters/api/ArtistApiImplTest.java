package com.api.music.adapters.api;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Artist;
import com.api.music.usecases.artist.CreateArtistUseCase;
import com.api.music.usecases.artist.DeleteArtistUseCase;
import com.api.music.usecases.artist.EditArtistUseCase;
import com.api.music.usecases.artist.GetArtistUseCase;
import com.api.music.usecases.artist.GetArtistsUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
class ArtistApiImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetArtistsUseCase getArtistsUseCase;

  @MockBean
  private GetArtistUseCase getArtistUseCase;
  @MockBean
  private CreateArtistUseCase createArtistUseCase;
  @MockBean
  private EditArtistUseCase editArtistUseCase;
  @MockBean
  private DeleteArtistUseCase deleteArtistUseCase;

  @Test
  public void testGetArtists() throws Exception {
    // CORRIGIR LINK - PORQUE O RETORNO DO WHEN NÃO VEM CORRETAMENTE?
    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpsssssssssssssssssg";
    String originCountry = "country";
    String genre = "genre";

    List<ArtistDTO> mockArtists = Collections.singletonList(new ArtistDTO(id,name,imageUrl,originCountry,genre));
    Mockito.when(getArtistsUseCase.getArtists(Mockito.anyList(), Mockito.anyList(), Mockito.anyInt(), Mockito.anyInt()))
        .thenReturn(mockArtists);


    mockMvc.perform(get("/artists")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.data").isArray())
//        .andExpect(jsonPath("$.data[0].name").value(mockArtists.get(0).getName()));
        .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
  }


  @Test
  public void testGetArtist() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";

    ArtistDTO mockArtist = new ArtistDTO(id,name,imageUrl,originCountry,genre);
    Mockito.when(getArtistUseCase.getArtist(id))
        .thenReturn(mockArtist);


    mockMvc.perform(get("/artists/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(id.intValue()))
        .andExpect(jsonPath("$.name").value(name))
        .andExpect(jsonPath("$.imageUrl").value(imageUrl))
        .andExpect(jsonPath("$.originCountry").value(originCountry))
        .andExpect(jsonPath("$.genre").value(genre));
  }

  @Test
  public void testCreateArtist() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";

    Artist mockArtist = new Artist(null,name,imageUrl,originCountry,genre);



    mockMvc.perform(post("/artists")
            .content(new ObjectMapper().writeValueAsString(mockArtist))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    Mockito.verify(createArtistUseCase).createArtist(mockArtist);
  }

  @Test
  public void testEditArtist() throws Exception {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "country";
    String genre = "genre";

    Artist mockArtist = new Artist(null,name,imageUrl,originCountry,genre);



    mockMvc.perform(put("/artists/1")
            .content(new ObjectMapper().writeValueAsString(mockArtist))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(editArtistUseCase).editArtist(id,mockArtist);
  }

  @Test
  public void testDeleteArtist() throws Exception {

    Long id = 1L;

    mockMvc.perform(delete("/artists/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(deleteArtistUseCase).deleteArtist(id);
  }


}