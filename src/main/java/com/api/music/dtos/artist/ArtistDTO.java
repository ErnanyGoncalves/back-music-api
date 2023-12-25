package com.api.music.dtos.artist;


import com.api.music.models.Artist;
import lombok.Getter;

@Getter
public class ArtistDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private String originCountry;
    private String genre;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.imageUrl = artist.getImageUrl();
        this.originCountry = artist.getOriginCountry();
        this.genre = artist.getGenre();
    }

    public ArtistDTO() {
    }
}
