package com.api.music.adapters.Artist;

import com.api.music.models.Artist;
import com.api.music.ports.ArtistPort;
import com.api.music.usecases.Artist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/artists")
public class ArtistImpl implements ArtistPort {

    private final GetArtistsUseCase getArtistsUseCase;
    private final GetArtistUseCase getArtistUseCase;
    private final CreateArtistUseCase createArtistUseCase;
    private final EditArtistUseCase editArtistUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;

    @Autowired
    public ArtistImpl(GetArtistsUseCase getArtistsUseCase, GetArtistUseCase getArtistUseCase, CreateArtistUseCase createArtistUseCase, EditArtistUseCase editArtistUseCase, DeleteArtistUseCase deleteArtistUseCase) {
        this.getArtistsUseCase = getArtistsUseCase;
        this.getArtistUseCase = getArtistUseCase;
        this.createArtistUseCase = createArtistUseCase;
        this.editArtistUseCase = editArtistUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
    }


    @GetMapping
    public List<Artist> getArtists() {
        return getArtistsUseCase.getArtists();
    }



    @GetMapping
    public List<Artist> getArtists(@RequestParam("country") List<String> originCountries, @RequestParam("genre") List<String> genres) {
        return getArtistsUseCase.getArtists(originCountries,genres);
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
