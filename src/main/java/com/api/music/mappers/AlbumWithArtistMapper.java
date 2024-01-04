package com.api.music.mappers;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.models.Album;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlbumWithArtistMapper {

  AlbumWithArtistDTO toDto(Album album);
}
