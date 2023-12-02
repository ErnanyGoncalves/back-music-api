package com.api.music.repository.music;

import com.api.music.models.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicRepositoryImpl implements MusicRepositoryPort {


    private final MusicRepository musicRepository;

    @Autowired
    public MusicRepositoryImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public List<Music> findAll(Integer page, Integer pageSize) {
        return musicRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public List<Music> findAll(List<String> albums, List<String> artists, Integer page, Integer pageSize) {
          return musicRepository.findByAlbumInAndArtistIn(albums, artists, PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public Optional<Music> findById(Long id) {
        return musicRepository.findById(id);
    }

    @Override
    public void save(Music music) {
        musicRepository.save(music);
    }

    @Override
    public void edit(Long id, Music music) {
        music.setId(id);
        musicRepository.save(music);
    }

    @Override
    public void delete(Long id) {
        musicRepository.deleteById(id);
    }
}


