package com.api.music.repository;

import com.api.music.models.Artist;
import com.api.music.repository.usecases.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistRepositoryImpl implements ArtistRepositoryPort{

    private final FindAllUseCase findAllUseCase;
    private final FindByIdUseCase findByIdUseCase;
    private final SaveUseCase saveUseCase;
    private final EditUseCase editUseCase;
    private final DeleteUseCase deleteUseCase;

    public ArtistRepositoryImpl(FindAllUseCase findAllUseCase, FindByIdUseCase findByIdUseCase, SaveUseCase saveUseCase, EditUseCase editUseCase, DeleteUseCase deleteUseCase) {
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.saveUseCase = saveUseCase;
        this.editUseCase = editUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @Override
    public List<Artist> findAll() {
        return findAllUseCase.findAll();
    }

    @Override
    public List<Artist> findAll(List<String> originCountries, List<String> genres) {
        return findAllUseCase.findAll(originCountries, genres);
    }

    @Override
    public Artist findById(Long id) {
        return findByIdUseCase.findById(id);
    }

    @Override
    public void save(Artist artist) {
        saveUseCase.save(artist);
    }

    @Override
    public void edit(Long id, Artist newArtistData) {
        editUseCase.edit(id,newArtistData);
    }

    @Override
    public void delete(Long id) {
        deleteUseCase.delete(id);
    }
}
