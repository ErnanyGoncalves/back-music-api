package com.api.music.adapters.api;

import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import com.api.music.models.Pagination;
import com.api.music.ports.MusicApiPort;
import com.api.music.usecases.music.CreateMusicUseCase;
import com.api.music.usecases.music.DeleteMusicUseCase;
import com.api.music.usecases.music.EditMusicUseCase;
import com.api.music.usecases.music.GetMusicUseCase;
import com.api.music.usecases.music.GetMusicsUseCase;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MusicApiImpl implements MusicApiPort {

  private final GetMusicsUseCase getMusicsUseCase;
  private final GetMusicUseCase getMusicUseCase;
  private final CreateMusicUseCase createMusicUseCase;
  private final EditMusicUseCase editMusicUseCase;
  private final DeleteMusicUseCase deleteMusicUseCase;


  @GetMapping
  public ResponseListDTO<MusicDTO> getMusics(
      @RequestParam(name = "album", required = false) List<String> albums,
      @RequestParam(name = "artist", required = false) List<String> artists,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    List<MusicDTO> listOfMusics = getMusicsUseCase.getMusics(albums, artists, page - 1, pageSize);
    return new ResponseListDTO<>(listOfMusics, new Pagination(page,pageSize));
  }

  @GetMapping("/{id}")
  public MusicDTO getMusic(@PathVariable("id") Long id) {
    return getMusicUseCase.getMusic(id);

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
