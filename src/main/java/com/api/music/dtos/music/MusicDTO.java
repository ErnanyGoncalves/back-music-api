package com.api.music.dtos.music;


import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusicDTO {

  private final Long id;
  private final String title;
  private final Integer trackNum;
  private final Integer duration;
  private final AlbumDTO album;
  private final ArtistDTO artist;
}
