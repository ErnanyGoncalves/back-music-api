package com.api.music.repository.music;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.models.Music;
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
public class MusicRepositoryImpl implements MusicRepositoryPort {


  private final MusicRepository musicRepository;
  private final EntityManager entityManager;

  @Lazy
  public MusicRepositoryImpl(MusicRepository musicRepository, EntityManager entityManager) {

    this.musicRepository = musicRepository;
    this.entityManager = entityManager;
  }

  @Override
  public List<Music> findAll(List<String> albums, List<String> artists, Integer page,
      Integer pageSize) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Music> criteriaQuery = criteriaBuilder.createQuery(Music.class);

    Root<Music> root = criteriaQuery.from(Music.class);
    List<Predicate> predicates = new ArrayList<>();

    Join<Music, Album> albumJoin = root.join("album");
    Join<Music, Artist> artistJoin = root.join("artist");

    if (Objects.nonNull(albums) && !albums.isEmpty()) {
      predicates.add(albumJoin.get("title").in(albums));
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
    TypedQuery<Music> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(page * pageSize);
    queryResult.setMaxResults(pageSize);

    return queryResult.getResultList();
  }

  @Override
  public Long count(List<String> albums, List<String> artists) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

    Root<Music> root = countQuery.from(Music.class);
    List<Predicate> predicates = new ArrayList<>();

    Join<Music, Album> albumJoin = root.join("album");
    Join<Music, Artist> artistJoin = root.join("artist");

    if (Objects.nonNull(albums) && !albums.isEmpty()) {
      predicates.add(albumJoin.get("title").in(albums));
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
  public Optional<Music> findById(Long id) {
    return musicRepository.findById(id);
  }

  @Override
  public void save(Music music) {
    musicRepository.save(music);
  }

  @Override
  public void edit(Long id, Music music) {
    music.setId(id);
    musicRepository.save(music);
  }

  @Override
  public void delete(Long id) {
    musicRepository.deleteById(id);
  }
}


