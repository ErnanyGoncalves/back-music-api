package com.api.music.usecases.music;

import com.api.music.dtos.music.MusicDTO;
import com.api.music.mappers.MusicMapper;
import com.api.music.repository.music.MusicRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMusicUseCase {

    private final MusicRepositoryPort musicRepository;
    private final MusicMapper musicMapper;

    public MusicDTO getMusic(Long id) {
        return musicRepository.findById(id).map(this.musicMapper::toDto).orElseThrow();
    }
}
