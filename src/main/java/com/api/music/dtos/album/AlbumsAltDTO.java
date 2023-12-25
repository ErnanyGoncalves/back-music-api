package com.api.music.dtos.album;

import lombok.Getter;

import java.util.List;

@Getter
public class AlbumsAltDTO {

    private List<AlbumDTO> albums;
    private Integer currentPage;
    private Integer itemsPerPage;
    private Long totalItems;

    public AlbumsAltDTO(List<AlbumDTO> albums, Integer currentPage, Integer itemsPerPage, Long totalItems) {
        this.albums = albums;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }

    public AlbumsAltDTO() {
    }
}
