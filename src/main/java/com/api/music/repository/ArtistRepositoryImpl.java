package com.api.music.repository;

import com.api.music.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistRepositoryImpl implements ArtistRepositoryPort{


    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistRepositoryImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> findAll(List<String> originCountries, List<String> genres) {
          return artistRepository.findByCountryInAndGenreIn(originCountries, genres);
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public void save(Artist artist) {
        this.artistRepository.save(artist);
    }

    @Override
    public void edit(Long id, Artist artist) {
        artist.setId(id);
        this.artistRepository.save(artist);
    }

    @Override
    public void delete(Long id) {
        this.artistRepository.deleteById(id);
    }
}


