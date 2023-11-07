package com.api.music.adapters.Artist;

import com.api.music.models.Artist;
import com.api.music.ports.Artist.ArtistPort;
import com.api.music.usecases.Artist.GetArtistsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArtistAdapter implements ArtistPort {

    private GetArtistsUseCase getArtistsUseCase;


    public List<Artist> getArtists() {
        return null;
    }


    @Override
    public List<Artist> getArtists(List<String> originCountries, List<String> genres) {
        return null;
    }

    @Override
    public Artist getArtist() {
        return null;
    }

    @Override
    public void createArtist(Artist artist) {

    }

    @Override
    public void editArtist(Long id, Artist newArtistData) {

    }

    @Override
    public void deleteArtist(Long id) {

    }
}
