package com.api.music.ports;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Artist;

import java.util.List;


public interface ArtistApiPort {
    ResponseListDTO<ArtistDTO> getArtists(Integer page, Integer pageSize);
    ResponseListDTO<ArtistDTO> getArtists(List<String> originCountries, List<String> genres, Integer page, Integer pageSize);
    ArtistDTO getArtist(Long id);
    void createArtist(Artist artist);
    void editArtist(Long id, Artist newArtistData);
    void deleteArtist(Long id);
}
