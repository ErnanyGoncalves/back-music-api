package com.api.music.mappers;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArtistMapper {
    ArtistDTO toDto(Artist artist);
}