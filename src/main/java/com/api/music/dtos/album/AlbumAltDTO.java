package com.api.music.dtos.album;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Album;
import com.api.music.models.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumAltDTO {

    private Long id;
    private String title;
    private String imageUrl;
    private Integer year;
    private Integer numOfTracks;
    private Integer totalDuration;
    private ArtistDTO artist;

    
}
