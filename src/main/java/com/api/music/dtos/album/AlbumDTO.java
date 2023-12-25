package com.api.music.dtos.album;

import com.api.music.models.Album;
import lombok.Getter;

@Getter
public class AlbumDTO {

    private Long id;
    private String title;
    private String imageUrl;
    private Integer year;
    private Integer numOfTracks;
    private Integer totalDuration;

    public AlbumDTO(Album album) {
        this.id=album.getId();
        this.title=album.getTitle();
        this.imageUrl = album.getImageUrl();
        this.year = album.getYear();
        this.numOfTracks = album.getNumOfTracks();
        this.totalDuration = album.getTotalDuration();
    }

    public AlbumDTO() {
    }
}
