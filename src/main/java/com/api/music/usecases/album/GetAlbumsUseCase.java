package com.api.music.usecases.album;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.repository.album.AlbumRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAlbumsUseCase {
    private final AlbumRepositoryPort albumRepository;
    private final AlbumWithArtistMapper albumMapper;

    public List<AlbumWithArtistDTO> getAlbums(Integer page, Integer pageSize) {
        return albumRepository.findAll(page, pageSize).stream().map(this.albumMapper::toDto).toList();
    }

    public List<AlbumWithArtistDTO> getAlbums(List<Integer> years, List<String> artists, Integer page, Integer pageSize) {
        return albumRepository.findAll(years, artists, page, pageSize).stream().map(this.albumMapper::toDto).toList();
    }
}
