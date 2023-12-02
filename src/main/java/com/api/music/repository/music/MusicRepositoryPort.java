package com.api.music.repository.music;


import com.api.music.models.Music;

import java.util.List;
import java.util.Optional;

public interface MusicRepositoryPort {
    List<Music> findAll(Integer page, Integer pageSize);
    List<Music> findAll(List<String> albums, List<String> artists,Integer page, Integer pageSize);
    Optional<Music> findById(Long id);
    void save(Music music);
    void edit(Long id, Music music);
    void delete(Long id);
}
