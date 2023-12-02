package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class EditAlbumUseCase {

    private final AlbumRepositoryPort albumRepository;
    public EditAlbumUseCase(AlbumRepositoryPort albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void editAlbum(Long id, Album newAlbumData){
        albumRepository.edit(id,newAlbumData);
    }
}
