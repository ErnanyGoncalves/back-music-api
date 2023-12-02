package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;


    public GetAlbumUseCase(AlbumRepositoryPort albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbum(Long id){
        final var album = albumRepository.findById(id);

        return album.orElseThrow();
    }
}
