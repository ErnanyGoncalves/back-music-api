package com.api.music.usecases.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Navigation;
import com.api.music.models.Pagination;
import com.api.music.repository.artist.ArtistRepositoryPort;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetArtistsUseCase {

  private final ArtistRepositoryPort artistRepository;
  private final ArtistMapper artistMapper;


  public ResponseListDTO<ArtistDTO> getArtists(List<String> countries, List<String> genres,
      Integer page,
      Integer pageSize) {
    List<ArtistDTO> listOfArtists = artistRepository.findAll(countries, genres, page, pageSize)
        .stream()
        .map(this.artistMapper::toDto).toList();
    Long totalElements = artistRepository.count(countries, genres);

    Integer currentPage = page + 1;
    Integer totalPages = (int) Math.ceil((double) totalElements / pageSize);

    String filters = buildFilters(pageSize, countries, genres);
    Navigation navigationPaths = buildNavigation(currentPage, totalPages, filters);

    return new ResponseListDTO<>(listOfArtists,
        new Pagination(currentPage, pageSize, totalElements, totalPages, navigationPaths));
  }

  private String buildFilters(Integer pageSize, List<String> originCountries, List<String> genres) {

    StringBuilder filterString = new StringBuilder("");

    if (pageSize != null && pageSize != 10) {
      filterString.append("&pageSize=" + String.valueOf(pageSize));
    }

    if (originCountries != null && !originCountries.isEmpty()) {
      filterString.append("&country=" + String.join(",", originCountries));
    }

    if (genres != null && !genres.isEmpty()) {
      filterString.append("&genre=" + String.join(",", genres));
    }

    return filterString.toString();
  }


  private Navigation buildNavigation(Integer currentPage, Integer totalPages, String filters) {
    Integer pPage = null;
    Integer cPage = currentPage;
    Integer nPage = null;

    if (cPage > 1) {
      pPage = cPage - 1;
    }
    if (cPage < totalPages) {
      nPage = cPage + 1;
    }

    String pPageStr = null;
    String cPageStr = new StringBuilder("/artists?page=" + cPage + filters).toString();
    String nPageStr = null;

    if (pPage != null) {
      pPageStr = new StringBuilder("/artists?page=" + pPage + filters).toString();
    }
    if (nPage != null) {
      nPageStr = new StringBuilder("/artists?page=" + nPage + filters).toString();
    }
    return new Navigation(pPageStr, cPageStr, nPageStr);
  }
}
