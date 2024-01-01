package com.api.music.mappers;

import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import org.mapstruct.Mapper;

@Mapper
public interface MusicMapper {
    MusicDTO toDto(Music music);
}
