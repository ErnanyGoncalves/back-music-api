package com.api.music.usecases.album;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.repository.album.AlbumRepositoryPort;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAlbumsUseCase {

  private final AlbumRepositoryPort albumRepository;
  private final AlbumWithArtistMapper albumMapper;


  public List<AlbumWithArtistDTO> getAlbums(List<Integer> years, List<String> artists, Integer page,
      Integer pageSize) {
    return albumRepository.findAll(years, artists, page, pageSize).stream()
        .map(this.albumMapper::toDto).toList();
  }
}
