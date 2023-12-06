package com.api.music.adapters.api;

import com.api.music.models.Music;
import com.api.music.ports.MusicApiPort;
import com.api.music.usecases.music.CreateMusicUseCase;
import com.api.music.usecases.music.DeleteMusicUseCase;
import com.api.music.usecases.music.EditMusicUseCase;
import com.api.music.usecases.music.GetMusicUseCase;
import com.api.music.usecases.music.GetMusicsUseCase;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
public class MusicApiImpl implements MusicApiPort {

  private final GetMusicsUseCase getMusicsUseCase;
  private final GetMusicUseCase getMusicUseCase;
  private final CreateMusicUseCase createMusicUseCase;
  private final EditMusicUseCase editMusicUseCase;
  private final DeleteMusicUseCase deleteMusicUseCase;


  public MusicApiImpl(GetMusicsUseCase getMusicsUseCase, GetMusicUseCase getMusicUseCase,
      CreateMusicUseCase createMusicUseCase, EditMusicUseCase editMusicUseCase,
      DeleteMusicUseCase deleteMusicUseCase) {
    this.getMusicsUseCase = getMusicsUseCase;
    this.getMusicUseCase = getMusicUseCase;
    this.createMusicUseCase = createMusicUseCase;
    this.editMusicUseCase = editMusicUseCase;
    this.deleteMusicUseCase = deleteMusicUseCase;
  }


  @GetMapping
  public List<Music> getMusics(@RequestParam("page") Integer page,
      @RequestParam("pageSize") Integer pageSize) {
    return getMusicsUseCase.getMusics(page, pageSize);
  }

  @GetMapping("/filter")
  public List<Music> getMusics(@RequestParam("album") List<String> albums,
      @RequestParam("artist") List<String> artists, @RequestParam("page") Integer page,
      @RequestParam("pageSize") Integer pageSize) {
    return getMusicsUseCase.getMusics(albums, artists, page, pageSize);
  }

  @GetMapping("/{id}")
  public Music getMusic(@PathVariable("id") Long id) {
    return getMusicUseCase.getMusic(id);
  }

  @PostMapping
  public void createMusic(Music music) {
    createMusicUseCase.createMusic(music);
  }

  @PutMapping("/{id}")
  public void editMusic(@PathVariable("id") Long id, Music newMusicData) {
    editMusicUseCase.editMusic(id, newMusicData);
  }

  @DeleteMapping("/{id}")
  public void deleteMusic(@PathVariable("id") Long id) {
    deleteMusicUseCase.deleteMusic(id);
  }
}
