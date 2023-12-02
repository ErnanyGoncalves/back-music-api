package com.api.music.repository.album;

import com.api.music.models.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumRepositoryImpl implements AlbumRepositoryPort {


    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumRepositoryImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll(Integer page, Integer pageSize) {
        return albumRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public List<Album> findAll(List<Integer> years, List<String> artists, Integer page, Integer pageSize) {
          return albumRepository.findByYearInAndArtistIn(years, artists, PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void edit(Long id, Album album) {
        album.setId(id);
        albumRepository.save(album);
    }

    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}


