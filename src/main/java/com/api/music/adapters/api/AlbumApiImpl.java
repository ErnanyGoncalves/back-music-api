package com.api.music.adapters.api;

import com.api.music.models.Album;
import com.api.music.ports.AlbumApiPort;
import com.api.music.usecases.album.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumApiImpl implements AlbumApiPort {

    private final GetAlbumsUseCase getAlbumsUseCase;
    private final GetAlbumUseCase getAlbumUseCase;
    private final CreateAlbumUseCase createAlbumUseCase;
    private final EditAlbumUseCase editAlbumUseCase;
    private final DeleteAlbumUseCase deleteAlbumUseCase;


    public AlbumApiImpl(GetAlbumsUseCase getAlbumsUseCase, GetAlbumUseCase getAlbumUseCase, CreateAlbumUseCase createAlbumUseCase, EditAlbumUseCase editAlbumUseCase, DeleteAlbumUseCase deleteAlbumUseCase) {
        this.getAlbumsUseCase = getAlbumsUseCase;
        this.getAlbumUseCase = getAlbumUseCase;
        this.createAlbumUseCase = createAlbumUseCase;
        this.editAlbumUseCase = editAlbumUseCase;
        this.deleteAlbumUseCase = deleteAlbumUseCase;
    }


    @GetMapping
    public List<Album> getAlbums(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return getAlbumsUseCase.getAlbums(page,pageSize);
    }

    @GetMapping("/filter")
    public List<Album> getAlbums(@RequestParam("year") List<Integer> years, @RequestParam("artist") List<String> artists,@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return getAlbumsUseCase.getAlbums(years,artists,page,pageSize);
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable("id") Long id) {
        return getAlbumUseCase.getAlbum(id);
    }

    @PostMapping
    public void createAlbum(Album album) {
        createAlbumUseCase.createAlbum(album);
    }

    @PutMapping("/{id}")
    public void editAlbum(@PathVariable("id") Long id, Album newAlbumData) {
        editAlbumUseCase.editAlbum(id,newAlbumData);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable("id") Long id) {
        deleteAlbumUseCase.deleteAlbum(id);
    }
}
