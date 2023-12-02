package com.api.music.adapters.api;

import com.api.music.models.Artist;
import com.api.music.ports.ArtistApiPort;
import com.api.music.usecases.artist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/artists")
public class ArtistApiImpl implements ArtistApiPort {

    private final GetArtistsUseCase getArtistsUseCase;
    private final GetArtistUseCase getArtistUseCase;
    private final CreateArtistUseCase createArtistUseCase;
    private final EditArtistUseCase editArtistUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;

    @Autowired
    public ArtistApiImpl(GetArtistsUseCase getArtistsUseCase, GetArtistUseCase getArtistUseCase, CreateArtistUseCase createArtistUseCase, EditArtistUseCase editArtistUseCase, DeleteArtistUseCase deleteArtistUseCase) {
        this.getArtistsUseCase = getArtistsUseCase;
        this.getArtistUseCase = getArtistUseCase;
        this.createArtistUseCase = createArtistUseCase;
        this.editArtistUseCase = editArtistUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
    }


    @GetMapping
    public List<Artist> getArtists(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return getArtistsUseCase.getArtists(page,pageSize);
    }

    @GetMapping
    public List<Artist> getArtists(@RequestParam("country") List<String> originCountries, @RequestParam("genre") List<String> genres,@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return getArtistsUseCase.getArtists(originCountries,genres,page,pageSize);
    }

    @GetMapping("/{id}")
    public Artist getArtist(@PathVariable("id") Long id) {
        return getArtistUseCase.getArtist(id);
    }

    @PostMapping
    public void createArtist(Artist artist) {
        createArtistUseCase.createArtist(artist);
    }

    @PutMapping("/{id}")
    public void editArtist(@PathVariable("id") Long id, Artist newArtistData) {
        editArtistUseCase.editArtist(id,newArtistData);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable("id") Long id) {
        deleteArtistUseCase.deleteArtist(id);
    }
}
