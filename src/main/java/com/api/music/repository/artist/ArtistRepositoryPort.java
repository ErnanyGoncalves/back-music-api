package com.api.music.repository.artist;

import com.api.music.models.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistRepositoryPort {


  List<Artist> findAll(List<String> originCountries, List<String> genres, Integer page,
      Integer pageSize);

  Optional<Artist> findById(Long id);

  void save(Artist artist);

  void edit(Long id, Artist artist);

  void delete(Long id);
}
