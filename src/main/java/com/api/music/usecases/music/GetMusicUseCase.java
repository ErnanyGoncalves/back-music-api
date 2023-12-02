package com.api.music.usecases.music;

import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetMusicUseCase {

    private final MusicRepositoryPort musicRepository;


    public GetMusicUseCase(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music getMusic(Long id){
        final var music = musicRepository.findById(id);

        return music.orElseThrow();
    }
}
