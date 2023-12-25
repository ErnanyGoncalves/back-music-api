package com.api.music.dtos.music;

import lombok.Getter;

import java.util.List;

@Getter
public class MusicsDTO {
    private List<MusicDTO> musics;
    private Integer currentPage;
    private Integer itemsPerPage;
    private Long totalItems;

    public MusicsDTO(List<MusicDTO> musics, Integer currentPage, Integer itemsPerPage, Long totalItems) {
        this.musics = musics;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }
    public MusicsDTO() {
    }
}
