package com.api.music.adapters.api;

import com.api.music.models.Album;
import com.api.music.ports.AlbumApiPort;
import com.api.music.usecases.album.CreateAlbumUseCase;
import com.api.music.usecases.album.DeleteAlbumUseCase;
import com.api.music.usecases.album.EditAlbumUseCase;
import com.api.music.usecases.album.GetAlbumUseCase;
import com.api.music.usecases.album.GetAlbumsUseCase;
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
@RequestMapping("/albums")
public class AlbumApiImpl implements AlbumApiPort {

  private final GetAlbumsUseCase getAlbumsUseCase;
  private final GetAlbumUseCase getAlbumUseCase;
  private final CreateAlbumUseCase createAlbumUseCase;
  private final EditAlbumUseCase editAlbumUseCase;
  private final DeleteAlbumUseCase deleteAlbumUseCase;


  public AlbumApiImpl(GetAlbumsUseCase getAlbumsUseCase, GetAlbumUseCase getAlbumUseCase,
      CreateAlbumUseCase createAlbumUseCase, EditAlbumUseCase editAlbumUseCase,
      DeleteAlbumUseCase deleteAlbumUseCase) {
    this.getAlbumsUseCase = getAlbumsUseCase;
    this.getAlbumUseCase = getAlbumUseCase;
    this.createAlbumUseCase = createAlbumUseCase;
    this.editAlbumUseCase = editAlbumUseCase;
    this.deleteAlbumUseCase = deleteAlbumUseCase;
  }


  @GetMapping
  public List<Album> getAlbums(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    return getAlbumsUseCase.getAlbums(page - 1, pageSize);
  }

  @GetMapping("/filter")
  public List<Album> getAlbums(@RequestParam("year") List<Integer> years,
      @RequestParam("artist") List<String> artists,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    return getAlbumsUseCase.getAlbums(years, artists, page - 1, pageSize);
  }

  @GetMapping("/{id}")
  public Album getAlbum(@PathVariable("id") Long id) {
    return getAlbumUseCase.getAlbum(id);
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createAlbum(@Valid @RequestBody Album album) {
    createAlbumUseCase.createAlbum(album);
  }

  @PutMapping("/{id}")
  public void editAlbum(@PathVariable("id") Long id, @Valid @RequestBody Album newAlbumData) {
    editAlbumUseCase.editAlbum(id, newAlbumData);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAlbum(@PathVariable("id") Long id) {
    deleteAlbumUseCase.deleteAlbum(id);
  }
}
