package com.api.music.usecases.artist;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.mappers.ArtistMapper;
import com.api.music.models.Navigation;
import com.api.music.models.Pagination;
import com.api.music.repository.artist.ArtistRepositoryPort;
import com.api.music.utils.FilterUtils;
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
    Navigation navigationPaths = FilterUtils.buildNavigation("artists", currentPage, totalPages,
        filters);

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


}
