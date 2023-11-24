package com.api.music.repository;

import com.api.music.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ArtistRepositoryImpl implements ArtistRepositoryPort{
    @Repository
    public interface ArtistRepository extends JpaRepository<Artist,Long> {    }
    private final ArtistRepository repository;
//    private final EntityManager entityManager;

    @Override
    public List<Artist> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Artist> findAll(List<String> originCountries, List<String> genres) {
//        final var filter = entityManager.getCriteriaBuilder().createQuery(Artist.class);
//        filter.where()
//        return this.repository.findAll();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void save(Artist artist) {
        this.repository.save(artist);
    }

    @Override
    public void edit(Long id, Artist artist) {
        artist.setId(id);
        this.repository.save(artist);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}


