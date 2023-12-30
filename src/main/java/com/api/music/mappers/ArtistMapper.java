package com.api.music.mappers;

import com.api.music.dtos.artist.ArtistDTO;
import com.api.music.models.Artist;
import org.mapstruct.Mapper;

@Mapper
public interface ArtistMapper {


  ArtistDTO toDto(Artist artist);

}