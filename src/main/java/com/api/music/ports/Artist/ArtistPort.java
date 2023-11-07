package com.api.music.ports.Artist;

import com.api.music.models.Artist;

import java.util.List;


public interface ArtistPort {
    List<Artist> getArtists();
    List<Artist> getArtists(List<String> originCountries,List<String> genres);
    Artist getArtist();
    void createArtist(Artist artist);
    void editArtist(Long id, Artist newArtistData);
    void deleteArtist(Long id);
}
