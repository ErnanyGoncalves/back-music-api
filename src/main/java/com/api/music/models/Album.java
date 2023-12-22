package com.api.music.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Field title is required.")
    private String title;
    private String imageUrl = "https://placehold.co/500";
    @Min(value=0,message="Field year is required.")
    private Integer year;
    private Integer numOfTracks = 0;
    private Integer totalDuration = 0;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @NotNull(message="The album must have an artist.")
    private Artist artist;


}
