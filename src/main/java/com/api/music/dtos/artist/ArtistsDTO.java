package com.api.music.dtos.artist;

import lombok.Getter;

import java.util.List;

@Getter
public class ArtistsDTO {
    private List<ArtistDTO> artists;
    private Integer currentPage;
    private Integer itemsPerPage;
    private Long totalItems;

    public ArtistsDTO(List<ArtistDTO> artists, Integer currentPage, Integer itemsPerPage, Long totalItems) {
        this.artists = artists;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }
    public ArtistsDTO() {
    }
}
