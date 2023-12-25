package com.api.music.dtos.album;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Album;
import com.api.music.models.Artist;
import lombok.Getter;

@Getter
public class AlbumAltDTO {

    private Long id;
    private String title;
    private String imageUrl;
    private Integer year;
    private Integer numOfTracks;
    private Integer totalDuration;
    private ArtistDTO artist;

    public AlbumAltDTO(Album album) {
        this.id=album.getId();
        this.title=album.getTitle();
        this.imageUrl = album.getImageUrl();
        this.year = album.getYear();
        this.numOfTracks = album.getNumOfTracks();
        this.totalDuration = album.getTotalDuration();
        this.artist = new ArtistDTO(album.getArtist());
    }

    public AlbumAltDTO() {
    }
}
