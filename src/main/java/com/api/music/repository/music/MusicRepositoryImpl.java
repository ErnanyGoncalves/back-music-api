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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Pageable pageable = PageRequest.of(page, pageSize);
      if (predicates.isEmpty()) {
          return musicRepository.findAll(pageable).getContent();
      }
    criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
    TypedQuery<Music> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(Long.valueOf(pageable.getOffset()).intValue());
    queryResult.setMaxResults(pageable.getPageSize());
    return queryResult.getResultList();
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


