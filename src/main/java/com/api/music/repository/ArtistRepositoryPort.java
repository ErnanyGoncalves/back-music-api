package com.api.music.repository;

import com.api.music.models.Artist;

import java.util.List;

public interface ArtistRepositoryPort {
    List<Artist> findAll();
    List<Artist> findAll(List<String> originCountries, List<String> genres);
    Artist findById(Long id);
    void save(Artist artist); // Pra uma resposta de save, edit e delete. Eu criaria uma Entity ou um DTO? Pra resp não ser um void.
    void edit(Long id, Artist artist);
    void delete(Long id);
}
