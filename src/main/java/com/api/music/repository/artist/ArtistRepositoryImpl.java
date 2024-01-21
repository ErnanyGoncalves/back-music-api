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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    // ---------------------------------
//    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//    countQuery.select(criteriaBuilder.count(countQuery.from(Artist.class)));
//    if (!predicates.isEmpty()) {
//      countQuery.where(predicates.toArray(new Predicate[0]));
//    }
//    Long totalElements = entityManager.createQuery(countQuery).getSingleResult();
//    Integer currentPage = page+1;
//    Integer totalPages = (int) Math.ceil((double) totalElements/pageSize);

    String queryParamsStr = buildArtistEndpoint(originCountries, genres, page, pageSize);


    // ---------------------------------

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

  // ------------------------------
  private static String buildArtistEndpoint(List<String> originCountries, List<String> genres, Integer page, Integer pageSize) {
    Map<String, String> queryParams = new HashMap<>();

    if (originCountries != null && !originCountries.isEmpty()) {
      queryParams.put("country", String.join(",", originCountries));
    }

    if (genres != null && !genres.isEmpty()) {
      queryParams.put("genre", String.join(",", genres));
    }

    if (page != null) {
      queryParams.put("page", String.valueOf(page));
    }

    if (pageSize != null && pageSize != 10) {
      queryParams.put("pageSize", String.valueOf(pageSize));
    }

    return buildQueryString(queryParams);
  }

  private static String buildQueryString(Map<String, String> queryParams) {
    if (queryParams.isEmpty()) {
      return "/artists";
    } else {
      StringBuilder queryString = new StringBuilder("/artists?");
      for (Map.Entry<String, String> entry : queryParams.entrySet()) {
        queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      queryString.deleteCharAt(queryString.length() - 1); // Remover o último "&"
      return queryString.toString();
    }
  }
 // -----------------------------
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


