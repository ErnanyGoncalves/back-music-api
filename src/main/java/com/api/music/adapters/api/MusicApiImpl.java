package com.api.music.adapters.api;

import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import com.api.music.ports.MusicApiPort;
import com.api.music.usecases.music.CreateMusicUseCase;
import com.api.music.usecases.music.DeleteMusicUseCase;
import com.api.music.usecases.music.EditMusicUseCase;
import com.api.music.usecases.music.GetMusicUseCase;
import com.api.music.usecases.music.GetMusicsUseCase;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public List<Music> getMusics(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    return getMusicsUseCase.getMusics(page - 1, pageSize);
  }

  @GetMapping("/filter")
  public List<Music> getMusics(@RequestParam("album") List<String> albums,
      @RequestParam("artist") List<String> artists,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name="pageSize", defaultValue = "10") Integer pageSize) {
    return getMusicsUseCase.getMusics(albums, artists, page - 1, pageSize);
  }

  @GetMapping("/{id}")
  public MusicDTO getMusic(@PathVariable("id") Long id) {
    Music music = getMusicUseCase.getMusic(id);
    return new MusicDTO(music);
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createMusic(@Valid @RequestBody Music music) {
    createMusicUseCase.createMusic(music);
  }

  @PutMapping("/{id}")
  public void editMusic(@PathVariable("id") Long id, @Valid @RequestBody Music newMusicData) {
    editMusicUseCase.editMusic(id, newMusicData);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteMusic(@PathVariable("id") Long id) {
    deleteMusicUseCase.deleteMusic(id);
  }
}
