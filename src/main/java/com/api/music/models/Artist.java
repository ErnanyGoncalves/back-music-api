package com.api.music.models;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private String originCountry;
    private String genre;

    public Artist(String name, String imageUrl, String originCountry, String genre) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.originCountry = originCountry;
        this.genre = genre;
    }
}
