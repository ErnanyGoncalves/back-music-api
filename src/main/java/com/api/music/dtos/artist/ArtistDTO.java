package com.api.music.dtos.artist;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArtistDTO {

  private final Long id;
  private final String name;
  private final String imageUrl;
  private final String originCountry;
  private final String genre;

}
