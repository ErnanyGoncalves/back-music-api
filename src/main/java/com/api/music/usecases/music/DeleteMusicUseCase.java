package com.api.music.usecases.music;

import com.api.music.repository.music.MusicRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteMusicUseCase {

  private final MusicRepositoryPort musicRepository;

  public void deleteMusic(Long id) {
    musicRepository.delete(id);
  }
}
