package com.api.music.usecases.music;

import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateMusicUseCase {

    private final MusicRepositoryPort musicRepository;
    public CreateMusicUseCase(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void createMusic( Music music){
        musicRepository.save(music);
    }
}
