package com.api.music.usecases.music;

import com.api.music.repository.music.MusicRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteMusicUseCase {

    private final MusicRepositoryPort musicRepository;

    public DeleteMusicUseCase(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void deleteMusic(Long id) {
        musicRepository.delete(id);
    }
}
