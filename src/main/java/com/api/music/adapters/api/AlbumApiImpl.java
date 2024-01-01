package com.api.music.adapters.api;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Album;
import com.api.music.ports.AlbumApiPort;
import com.api.music.usecases.album.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@AllArgsConstructor
public class AlbumApiImpl implements AlbumApiPort {

    private final GetAlbumsUseCase getAlbumsUseCase;
    private final GetAlbumUseCase getAlbumUseCase;
    private final CreateAlbumUseCase createAlbumUseCase;
    private final EditAlbumUseCase editAlbumUseCase;
    private final DeleteAlbumUseCase deleteAlbumUseCase;

    @GetMapping
    public ResponseListDTO<AlbumWithArtistDTO> getAlbums(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<AlbumWithArtistDTO> listOfAlbums = getAlbumsUseCase.getAlbums(page - 1, pageSize);
        return new ResponseListDTO<>(listOfAlbums, page, pageSize);
    }

    @GetMapping("/filter")
    public ResponseListDTO<AlbumWithArtistDTO> getAlbums(@RequestParam("year") List<Integer> years,
                                                         @RequestParam("artist") List<String> artists,
                                                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<AlbumWithArtistDTO> listOfAlbums = getAlbumsUseCase.getAlbums(years, artists, page - 1, pageSize);
        return new ResponseListDTO<>(listOfAlbums, page, pageSize);
    }

    @GetMapping("/{id}")
    public AlbumWithArtistDTO getAlbum(@PathVariable("id") Long id) {
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
