package com.api.music.dtos.album;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlbumDTOTest {

  @Test
  public void testAlbumDTO() {

    Long id = 1L;
    String title = "Title";
    String imageUrl = "http://example.com/image.jpg";
    Integer year = 2022;
    Integer numOfTracks = 10;
    Integer totalDuration = 3600;

    AlbumDTO albumDTO = new AlbumDTO(id, title, imageUrl, year, numOfTracks, totalDuration);

    assertEquals(id, albumDTO.getId());
    assertEquals(title, albumDTO.getTitle());
    assertEquals(imageUrl, albumDTO.getImageUrl());
    assertEquals(year, albumDTO.getYear());
    assertEquals(numOfTracks, albumDTO.getNumOfTracks());
    assertEquals(totalDuration, albumDTO.getTotalDuration());
  }
}