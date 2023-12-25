package com.api.music.dtos.music;


import com.api.music.dtos.album.AlbumDTO;
import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Music;
import lombok.Getter;

@Getter
public class MusicDTO {
    private Long id;
    private String title;
    private Integer trackNum;
    private Integer duration;
    private AlbumDTO album;
    private ArtistDTO artist;

    public MusicDTO(Music music) {
        this.id = music.getId();
        this.title = music.getTitle();
        this.trackNum = music.getTrackNum();
        this.duration = music.getDuration();
        this.album = new AlbumDTO(music.getAlbum());
        this.artist = new ArtistDTO(music.getArtist());

    }

    public MusicDTO() {
    }
}
