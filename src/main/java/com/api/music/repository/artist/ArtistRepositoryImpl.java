package com.api.music.repository.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.catalina.connector.Response;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ArtistRepositoryImpl implements ArtistRepositoryPort {


  private final ArtistRepository artistRepository;
  private final EntityManager entityManager;

  @Lazy
  public ArtistRepositoryImpl(ArtistRepository artistRepository, EntityManager entityManager) {
    this.artistRepository = artistRepository;
    this.entityManager = entityManager;
  }


  @Override
  public List<Artist> findAll(List<String> originCountries, List<String> genres, Integer page,
      Integer pageSize) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Artist> criteriaQuery = criteriaBuilder.createQuery(Artist.class);
    Root<Artist> root = criteriaQuery.from(Artist.class);
    List<Predicate> predicates = new ArrayList<>();

    if (Objects.nonNull(originCountries) && !originCountries.isEmpty()) {
      predicates.add(root.get("originCountry").in(originCountries));
    }

    if (Objects.nonNull(genres) && !genres.isEmpty()) {
      predicates.add(root.get("genre").in(genres));
    }

    Pageable pageable = PageRequest.of(page, pageSize);
    if (predicates.isEmpty()) {
      return artistRepository.findAll(pageable).getContent();
    }
    criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
    TypedQuery<Artist> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(Long.valueOf(pageable.getOffset()).intValue());
    queryResult.setMaxResults(pageable.getPageSize());
    
    return queryResult.getResultList();
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


