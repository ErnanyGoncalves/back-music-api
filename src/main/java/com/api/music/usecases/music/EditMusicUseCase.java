package com.api.music.usecases.music;

import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class EditMusicUseCase {

    private final MusicRepositoryPort musicRepository;
    public EditMusicUseCase(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void editMusic(Long id, Music newMusicData){
        musicRepository.edit(id,newMusicData);
    }
}
