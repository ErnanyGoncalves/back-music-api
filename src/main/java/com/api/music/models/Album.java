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
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Field title is required.")
  private String title;
//  @Pattern(regexp = "https?:\\/\\/.*\\.(?:png|jpg)", message = "Invalid URL.")
  private String imageUrl = "https://placehold.co/500";
  //  @Min(value = 1, message = "The year has to be greater than 0.")
  //  @NotNull(message = "Field year is required.")
  @Min(value = 0, message = "Field year is required.")
  private Integer year;
  private Integer numOfTracks = 0;
  private Integer totalDuration = 0;
  @ManyToOne
  @JoinColumn(name = "artist_id")
  @NotNull(message = "The album must have an artist.")
  private Artist artist;


  public Album(Long id, String title, String imageUrl, Integer year, Artist artist) {
    this.id = id;
    this.title = title;
    this.imageUrl = imageUrl;
    this.year = year;
    this.artist = artist;
  }
}
