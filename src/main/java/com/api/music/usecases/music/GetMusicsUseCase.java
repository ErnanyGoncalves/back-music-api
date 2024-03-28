package com.api.music.usecases.music;

import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.mappers.MusicMapper;
import com.api.music.models.Navigation;
import com.api.music.models.Pagination;
import com.api.music.repository.music.MusicRepositoryPort;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMusicsUseCase {

  private final MusicRepositoryPort musicRepository;
  private final MusicMapper musicMapper;

  public ResponseListDTO<MusicDTO> getMusics(List<String> albums, List<String> artists,
      Integer page,
      Integer pageSize) {
    List<MusicDTO> listOfMusics = musicRepository.findAll(albums, artists, page, pageSize).stream()
        .map(this.musicMapper::toDto).sorted(Comparator.comparing(m -> m.getId())).toList();

    Long totalElements = musicRepository.count(albums, artists);

    Integer currentPage = page + 1;
    Integer totalPages = (int) Math.ceil((double) totalElements / pageSize);

    String filters = buildFilters(pageSize, albums, artists);
    Navigation navigationPaths = buildNavigation(currentPage, totalPages, filters);
    return new ResponseListDTO<>(listOfMusics,
        new Pagination(currentPage, pageSize, totalElements, totalPages, navigationPaths));
  }

  private String buildFilters(Integer pageSize, List<String> albums, List<String> artists) {

    StringBuilder filterString = new StringBuilder("");

    if (pageSize != null && pageSize != 10) {
      filterString.append("&pageSize=" + String.valueOf(pageSize));
    }

    if (albums != null && !albums.isEmpty()) {
      filterString.append("&album=" + String.join(",", albums));
    }

    if (artists != null && !artists.isEmpty()) {
      filterString.append("&artist=" + String.join(",", artists));
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
    String cPageStr = new StringBuilder("/musics?page=" + cPage + filters).toString();
    String nPageStr = null;

    if (pPage != null) {
      pPageStr = new StringBuilder("/musics?page=" + pPage + filters).toString();
    }
    if (nPage != null) {
      nPageStr = new StringBuilder("/musics?page=" + nPage + filters).toString();
    }
    return new Navigation(pPageStr, cPageStr, nPageStr);
  }
}
