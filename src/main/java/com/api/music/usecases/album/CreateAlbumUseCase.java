package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;
    public CreateAlbumUseCase(AlbumRepositoryPort albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void createAlbum( Album album){
        albumRepository.save(album);
    }
}
