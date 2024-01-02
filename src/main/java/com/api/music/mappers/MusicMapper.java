package com.api.music.mappers;

import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MusicMapper {
    MusicDTO toDto(Music music);
}
