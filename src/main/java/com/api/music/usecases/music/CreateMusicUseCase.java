package com.api.music.usecases.music;

import com.api.music.models.Album;
import com.api.music.models.Music;
import com.api.music.repository.album.AlbumRepositoryPort;
import com.api.music.repository.music.MusicRepositoryPort;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CreateMusicUseCase {

  private final MusicRepositoryPort musicRepository;
  private final AlbumRepositoryPort albumRepository;

  public CreateMusicUseCase(MusicRepositoryPort musicRepository,
      AlbumRepositoryPort albumRepository) {
    this.musicRepository = musicRepository;
    this.albumRepository = albumRepository;
  }


  public void createMusic(Music music) {

  try {
    musicRepository.save(music);
  }catch(DataIntegrityViolationException e){
    throw new RuntimeException("asokdoaskdopaksd");
  }
  }
}
