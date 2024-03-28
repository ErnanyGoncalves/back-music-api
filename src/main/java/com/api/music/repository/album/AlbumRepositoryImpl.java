package com.api.music.repository.album;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AlbumRepositoryImpl implements AlbumRepositoryPort {


  private final AlbumRepository albumRepository;
  private final EntityManager entityManager;

  @Lazy
  public AlbumRepositoryImpl(AlbumRepository albumRepository, EntityManager entityManager) {
    this.albumRepository = albumRepository;
    this.entityManager = entityManager;
  }


  @Override
  public List<Album> findAll(List<Integer> years, List<String> artists, Integer page,
      Integer pageSize) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Album> criteriaQuery = criteriaBuilder.createQuery(Album.class);

    Root<Album> root = criteriaQuery.from(Album.class);
    List<Predicate> predicates = new ArrayList<>();

    Join<Album, Artist> artistJoin = root.join("artist");

    if (Objects.nonNull(years) && !years.isEmpty()) {
      predicates.add(root.get("year").in(years));
    }
    if (Objects.nonNull(artists) && !artists.isEmpty()) {
      predicates.add(artistJoin.get("name").in(artists));
    }

    Predicate predicate = null;
    if (!predicates.isEmpty()) {
      predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }
    if (Objects.nonNull(predicate)) {
      criteriaQuery.where(predicate);
    }

    criteriaQuery.select(root);
    TypedQuery<Album> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(page * pageSize);
    queryResult.setMaxResults(pageSize);

    return queryResult.getResultList();

  }

  @Override
  public Long count(List<Integer> years, List<String> artists) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

    Root<Album> root = countQuery.from(Album.class);
    List<Predicate> predicates = new ArrayList<>();

    Join<Album, Artist> artistJoin = root.join("artist");

    if (Objects.nonNull(years) && !years.isEmpty()) {
      predicates.add(root.get("year").in(years));
    }
    if (Objects.nonNull(artists) && !artists.isEmpty()) {
      predicates.add(artistJoin.get("name").in(artists));
    }

    Predicate predicate = null;
    if (!predicates.isEmpty()) {
      predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }
    if (Objects.nonNull(predicate)) {
      countQuery.where(predicate);
    }
    countQuery.select(criteriaBuilder.count(root));

    return entityManager.createQuery(countQuery).getSingleResult();

  }

  @Override
  public Optional<Album> findById(Long id) {
    return albumRepository.findById(id);
  }

  @Override
  public void save(Album album) {
    albumRepository.save(album);
  }

  @Override
  public void edit(Long id, Album album) {
    album.setId(id);
    albumRepository.save(album);
  }

  @Override
  public void delete(Long id) {
    albumRepository.deleteById(id);
  }
}


