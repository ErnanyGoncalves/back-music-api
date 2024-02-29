package com.api.music.dtos.music;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import org.junit.jupiter.api.Test;

class MusicDTOTest {
  @Test
  public void testMusicDTO() {

    Long id = 1L;
    String title = "Title";

    Integer trackNum = 1;
    Integer duration = 3600;
    AlbumDTO album = new AlbumDTO(1L,"Title","http://example.com/image.jpg",2000,10,3600);
    ArtistDTO artist = new ArtistDTO(1L,"Artist","http://example.com/image.jpg","Country","Genre");

    MusicDTO musicDTO = new MusicDTO(id, title, trackNum, duration, album,artist);

    assertEquals(id, musicDTO.getId());
    assertEquals(title, musicDTO.getTitle());
    assertEquals(trackNum, musicDTO.getTrackNum());
    assertEquals(duration, musicDTO.getDuration());
    assertEquals(album, musicDTO.getAlbum());
    assertEquals(artist, musicDTO.getArtist());
  }
}