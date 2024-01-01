package com.api.music.mappers;

import com.api.music.dtos.album.AlbumWithArtistDTO;
import com.api.music.models.Album;
import org.mapstruct.Mapper;

@Mapper
public interface AlbumWithArtistMapper {
    AlbumWithArtistDTO toDto(Album album);
}
