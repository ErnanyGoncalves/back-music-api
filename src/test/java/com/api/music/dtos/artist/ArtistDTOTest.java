package com.api.music.dtos.artist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArtistDTOTest {
  @Test
  public void testArtistDTO() {

    Long id = 1L;
    String name = "name";
    String imageUrl = "http://example.com/image.jpg";
    String originCountry = "Country";
    String genre = "Genre";

    ArtistDTO artistDTO = new ArtistDTO(id, name, imageUrl, originCountry,genre);

    assertEquals(id, artistDTO.getId());
    assertEquals(name, artistDTO.getName());
    assertEquals(imageUrl, artistDTO.getImageUrl());
    assertEquals(originCountry, artistDTO.getOriginCountry());
    assertEquals(genre, artistDTO.getGenre());
    
  }
}