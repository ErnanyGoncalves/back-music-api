package com.api.music.dtos.album;

import com.api.music.dtos.artist.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumWithArtistDTO {

  private final Long id;
  private final String title;
  private final String imageUrl;
  private final Integer year;
  private final Integer numOfTracks;
  private final Integer totalDuration;
  private final ArtistDTO artist;


}
