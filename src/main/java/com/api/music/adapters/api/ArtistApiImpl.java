package com.api.music.adapters.api;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Artist;
import com.api.music.models.Pagination;
import com.api.music.ports.ArtistApiPort;
import com.api.music.usecases.artist.CreateArtistUseCase;
import com.api.music.usecases.artist.DeleteArtistUseCase;
import com.api.music.usecases.artist.EditArtistUseCase;
import com.api.music.usecases.artist.GetArtistUseCase;
import com.api.music.usecases.artist.GetArtistsUseCase;
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
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistApiImpl implements ArtistApiPort {

  private final GetArtistsUseCase getArtistsUseCase;
  private final GetArtistUseCase getArtistUseCase;
  private final CreateArtistUseCase createArtistUseCase;
  private final EditArtistUseCase editArtistUseCase;
  private final DeleteArtistUseCase deleteArtistUseCase;


  @GetMapping
  public ResponseListDTO<ArtistDTO> getArtists(
      @RequestParam(name = "country", required = false) List<String> originCountries,
      @RequestParam(name = "genre", required = false) List<String> genres,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    List<ArtistDTO> listOfArtists = getArtistsUseCase.getArtists(originCountries, genres, page - 1,
        pageSize);
    return new ResponseListDTO<>(listOfArtists, new Pagination(page,pageSize));
  }

  @GetMapping("/{id}")
  public ArtistDTO getArtist(@PathVariable("id") Long id) {
    return getArtistUseCase.getArtist(id);

  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createArtist(@Valid @RequestBody Artist artist) {
    createArtistUseCase.createArtist(artist);
  }

  @PutMapping("/{id}")
  public void editArtist(@PathVariable("id") Long id, @Valid @RequestBody Artist newArtistData) {
    editArtistUseCase.editArtist(id, newArtistData);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteArtist(@PathVariable("id") Long id) {
    deleteArtistUseCase.deleteArtist(id);
  }
}
