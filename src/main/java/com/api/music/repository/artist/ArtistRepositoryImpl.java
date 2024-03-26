package com.api.music.repository.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.models.Artist;
import com.api.music.models.Navigation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.catalina.connector.Response;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Predicates;
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

    Predicate predicate = null;
    if (!predicates.isEmpty()) {
      predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }
    if(Objects.nonNull(predicate)){
      criteriaQuery.where(predicate);
    }

    criteriaQuery.select(root);
    TypedQuery<Artist> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(page*pageSize);
    queryResult.setMaxResults(pageSize);
    
    return queryResult.getResultList();
  }

  @Override
  public Long count(List<String> originCountries, List<String> genres){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

    Root<Artist> root = countQuery.from(Artist.class);
    List<Predicate> predicates = new ArrayList<>();

    if (Objects.nonNull(originCountries) && !originCountries.isEmpty()) {
      predicates.add(root.get("originCountry").in(originCountries));
    }

    if (Objects.nonNull(genres) && !genres.isEmpty()) {
      predicates.add(root.get("genre").in(genres));
    }

    Predicate predicate = null;
    if (!predicates.isEmpty()) {
      predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }
    if(Objects.nonNull(predicate)){
      countQuery.where(predicate);
    }
    countQuery.select(criteriaBuilder.count(root));

    return entityManager.createQuery(countQuery).getSingleResult();

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


