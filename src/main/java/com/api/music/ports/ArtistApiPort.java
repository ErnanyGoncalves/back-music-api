package com.api.music.ports;

import com.api.music.models.Artist;

import java.util.List;


public interface ArtistApiPort {
    List<Artist> getArtists(int page, int pageSize);
    List<Artist> getArtists(List<String> originCountries, List<String> genres, int page, int pageSize);
    Artist getArtist(Long id);
    void createArtist(Artist artist);
    void editArtist(Long id, Artist newArtistData);
    void deleteArtist(Long id);
}
