package com.api.music.usecases.music;

import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMusicsUseCase {
    private final MusicRepositoryPort musicRepository;
    public GetMusicsUseCase(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getMusics(Integer page, Integer pageSize){
        return musicRepository.findAll( page,  pageSize);
    }

    public List<Music> getMusics(List<String> albums,List<String> artists,Integer page, Integer pageSize){
        return musicRepository.findAll(albums,artists,page,pageSize);
    }
}
