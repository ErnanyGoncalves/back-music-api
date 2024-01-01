package com.api.music.ports;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Album;

import java.util.List;


public interface AlbumApiPort {
    ResponseListDTO<AlbumWithArtistDTO> getAlbums(Integer page, Integer pageSize);

    ResponseListDTO<AlbumWithArtistDTO> getAlbums(List<Integer> years, List<String> artists, Integer page, Integer pageSize);

    AlbumWithArtistDTO getAlbum(Long id);

    void createAlbum(Album album);

    void editAlbum(Long id, Album newAlbumData);

    void deleteAlbum(Long id);
}
