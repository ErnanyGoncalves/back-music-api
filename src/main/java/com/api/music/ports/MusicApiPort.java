package com.api.music.ports;

import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import java.util.List;


public interface MusicApiPort {

  ResponseListDTO<MusicDTO> getMusics(List<String> albums, List<String> artists, Integer page,
      Integer pageSize);

  MusicDTO getMusic(Long id);

  void createMusic(Music music);

  void editMusic(Long id, Music newMusicData);

  void deleteMusic(Long id);
}
