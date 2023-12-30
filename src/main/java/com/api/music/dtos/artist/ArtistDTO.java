package com.api.music.dtos.artist;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArtistDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private String originCountry;
    private String genre;

}
