package com.api.music.ports;


import com.api.music.models.Album;

import java.util.List;


public interface AlbumApiPort {
    List<Album> getAlbums(Integer page, Integer pageSize);
    List<Album> getAlbums(List<Integer> years, List<String> artists, Integer page, Integer pageSize);
    Album getAlbum(Long id);
    void createAlbum(Album album);
    void editAlbum(Long id, Album newAlbumData);
    void deleteAlbum(Long id);
}
