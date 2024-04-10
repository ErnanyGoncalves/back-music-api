package com.api.music.usecases.album;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.models.Navigation;
import com.api.music.models.Pagination;
import com.api.music.repository.album.AlbumRepositoryPort;
import com.api.music.utils.FilterUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAlbumsUseCase {

  private final AlbumRepositoryPort albumRepository;
  private final AlbumWithArtistMapper albumMapper;


  public ResponseListDTO<AlbumWithArtistDTO> getAlbums(List<Integer> years, List<String> artists,
      Integer page,
      Integer pageSize) {
    List<AlbumWithArtistDTO> listOfAlbums = albumRepository.findAll(years, artists, page, pageSize)
        .stream()
        .map(this.albumMapper::toDto).toList();

    Long totalElements = albumRepository.count(years, artists);

    Integer currentPage = page + 1;
    Integer totalPages = (int) Math.ceil((double) totalElements / pageSize);

    String filters = buildFilters(pageSize, years, artists);
    Navigation navigationPaths = FilterUtils.buildNavigation("albums", currentPage, totalPages,
        filters);

    return new ResponseListDTO<>(listOfAlbums,
        new Pagination(currentPage, pageSize, totalElements, totalPages, navigationPaths));
  }

  private String buildFilters(Integer pageSize, List<Integer> years, List<String> artists) {

    StringBuilder filterString = new StringBuilder("");

    if (pageSize != null && pageSize != 10) {
      filterString.append("&pageSize=" + String.valueOf(pageSize));
    }

    if (years != null && !years.isEmpty()) {
      filterString.append("&year=" + years.stream()
          .map(String::valueOf)
          .collect(Collectors.joining(",")));
    }

    if (artists != null && !artists.isEmpty()) {
      filterString.append("&artist=" + String.join(",", artists));
    }

    return filterString.toString();
  }

}
