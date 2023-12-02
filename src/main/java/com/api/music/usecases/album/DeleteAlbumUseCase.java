package com.api.music.usecases.album;

import com.api.music.repository.album.AlbumRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;

    public DeleteAlbumUseCase(AlbumRepositoryPort albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void deleteAlbum(Long id) {
        albumRepository.delete(id);
    }
}
