package com.api.music.dtos.album;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.api.music.dtos.artist.ArtistDTO;
import org.junit.jupiter.api.Test;

class AlbumWithArtistDTOTest {

  @Test
  public void testAlbumWithArtistDTO() {

    Long id = 1L;
    String title = "Title";
    String imageUrl = "http://example.com/image.jpg";
    Integer year = 2022;
    Integer numOfTracks = 10;
    Integer totalDuration = 3600;
    ArtistDTO artist = new ArtistDTO(1L, "Artist", "http://example.com/image.jpg", "Country",
        "Genre");

    AlbumWithArtistDTO albumWithArtistDTO = new AlbumWithArtistDTO(id, title, imageUrl, year,
        numOfTracks, totalDuration, artist);

    assertEquals(id, albumWithArtistDTO.getId());
    assertEquals(title, albumWithArtistDTO.getTitle());
    assertEquals(imageUrl, albumWithArtistDTO.getImageUrl());
    assertEquals(year, albumWithArtistDTO.getYear());
    assertEquals(numOfTracks, albumWithArtistDTO.getNumOfTracks());
    assertEquals(totalDuration, albumWithArtistDTO.getTotalDuration());
    assertEquals(artist, albumWithArtistDTO.getArtist());
  }
}