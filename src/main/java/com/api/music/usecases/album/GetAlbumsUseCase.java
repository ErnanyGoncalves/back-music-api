package com.api.music.usecases.album;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAlbumsUseCase {
    private final AlbumRepositoryPort albumRepository;
    public GetAlbumsUseCase(AlbumRepositoryPort albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAlbums(Integer page, Integer pageSize){
        return albumRepository.findAll( page,  pageSize);
    }

    public List<Album> getAlbums(List<Integer> years,List<String> artists,Integer page, Integer pageSize){
        return albumRepository.findAll(years,artists,page,pageSize);
    }
}
