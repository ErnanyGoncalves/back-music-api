package com.api.music.usecases.album;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.mappers.AlbumWithArtistMapper;
import com.api.music.repository.album.AlbumRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;
    private final AlbumWithArtistMapper albumMapper;


    public AlbumWithArtistDTO getAlbum(Long id) {
        return albumRepository.findById(id).map(this.albumMapper::toDto).orElseThrow();
    }
}
