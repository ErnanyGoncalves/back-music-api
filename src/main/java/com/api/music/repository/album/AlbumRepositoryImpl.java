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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Pageable pageable = PageRequest.of(page, pageSize);
      if (predicates.isEmpty()) {
        return albumRepository.findAll(pageable).getContent();
      }
    criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
    TypedQuery<Album> queryResult = this.entityManager.createQuery(criteriaQuery);

    queryResult.setFirstResult(Long.valueOf(pageable.getOffset()).intValue());
    queryResult.setMaxResults(pageable.getPageSize());

    return queryResult.getResultList();
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


