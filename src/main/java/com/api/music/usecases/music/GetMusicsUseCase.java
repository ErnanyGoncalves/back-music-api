package com.api.music.usecases.music;

import com.api.music.dtos.music.MusicDTO;
import com.api.music.mappers.MusicMapper;
import com.api.music.repository.music.MusicRepositoryPort;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMusicsUseCase {

  private final MusicRepositoryPort musicRepository;
  private final MusicMapper musicMapper;

  public List<MusicDTO> getMusics(List<String> albums, List<String> artists, Integer page,
      Integer pageSize) {
    return musicRepository.findAll(albums, artists, page, pageSize).stream()
        .map(this.musicMapper::toDto).toList();
  }
}
