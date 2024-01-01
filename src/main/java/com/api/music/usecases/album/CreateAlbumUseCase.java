package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;

    public void createAlbum(Album album) {
        albumRepository.save(album);
    }
}
