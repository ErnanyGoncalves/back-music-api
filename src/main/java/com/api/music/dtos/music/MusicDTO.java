package com.api.music.dtos.music;


import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusicDTO {
    private Long id;
    private String title;
    private Integer trackNum;
    private Integer duration;
    private AlbumDTO album;
    private ArtistDTO artist;
}
