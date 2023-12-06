package com.api.music.repository.artist;

import com.api.music.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class ArtistRepositoryImpl implements ArtistRepositoryPort{


    private final ArtistRepository artistRepository;

    @Lazy
    public ArtistRepositoryImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll(Integer page, Integer pageSize) {
        return artistRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public List<Artist> findAll(List<String> originCountries, List<String> genres, Integer page, Integer pageSize) {
          return artistRepository.findByOriginCountryInAndGenreIn(originCountries, genres, PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void save(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void edit(Long id, Artist artist) {
        artist.setId(id);
        artistRepository.save(artist);
    }

    @Override
    public void delete(Long id) {
        artistRepository.deleteById(id);
    }
}


