package com.api.music.repository.album;


import com.api.music.models.Album;
import java.util.List;
import java.util.Optional;

public interface AlbumRepositoryPort {

  List<Album> findAll(List<Integer> years, List<String> artists, Integer page, Integer pageSize);

  Long count(List<Integer> years, List<String> artists);

  Optional<Album> findById(Long id);

  void save(Album album);

  void edit(Long id, Album album);

  void delete(Long id);
}
