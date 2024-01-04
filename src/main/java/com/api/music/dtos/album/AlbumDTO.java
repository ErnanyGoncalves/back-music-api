package com.api.music.dtos.album;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumDTO {

  private final Long id;
  private final String title;
  private final String imageUrl;
  private final Integer year;
  private final Integer numOfTracks;
  private final Integer totalDuration;


}
