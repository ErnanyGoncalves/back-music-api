package com.api.music.usecases.album;

import com.api.music.repository.album.AlbumRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;

    public void deleteAlbum(Long id) {
        albumRepository.delete(id);
    }
}
