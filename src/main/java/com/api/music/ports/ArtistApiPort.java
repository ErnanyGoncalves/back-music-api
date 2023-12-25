package com.api.music.ports;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Artist;

import java.util.List;


public interface ArtistApiPort {
    List<Artist> getArtists(Integer page, Integer pageSize);
    List<Artist> getArtists(List<String> originCountries, List<String> genres, Integer page, Integer pageSize);
    ArtistDTO getArtist(Long id);
    void createArtist(Artist artist);
    void editArtist(Long id, Artist newArtistData);
    void deleteArtist(Long id);
}
