package com.api.music.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageUrl;
    private Integer year;
    private Integer numOfTracks;
    private Integer totalDuration;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Album(String title, String imageUrl, Integer year, Integer numOfTracks,
        Integer totalDuration, Artist artist) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.year = year;
        this.numOfTracks = numOfTracks;
        this.totalDuration = totalDuration;
        this.artist = artist;
    }
}
