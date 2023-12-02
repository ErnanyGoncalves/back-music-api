package com.api.music.ports;

import com.api.music.models.Music;

import java.util.List;


public interface MusicApiPort {
    List<Music> getMusics(Integer page, Integer pageSize);
    List<Music> getMusics(List<String> albums, List<String> artists, Integer page, Integer pageSize);
    Music getMusic(Long id);
    void createMusic(Music music);
    void editMusic(Long id, Music newMusicData);
    void deleteMusic(Long id);
}
