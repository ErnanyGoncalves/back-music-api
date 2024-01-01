package com.api.music.adapters.api;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Artist;
import com.api.music.ports.ArtistApiPort;
import com.api.music.usecases.artist.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseListDTO<ArtistDTO> getArtists(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<ArtistDTO> listOfArtists = getArtistsUseCase.getArtists(page - 1, pageSize);
        return new ResponseListDTO<>(listOfArtists, page, pageSize);
    }

    @GetMapping("/filter")
    public ResponseListDTO<ArtistDTO> getArtists(@RequestParam("country") List<String> originCountries, @RequestParam("genre") List<String> genres, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<ArtistDTO> listOfArtists = getArtistsUseCase.getArtists(originCountries, genres, page - 1, pageSize);
        return new ResponseListDTO<>(listOfArtists, page, pageSize);
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
