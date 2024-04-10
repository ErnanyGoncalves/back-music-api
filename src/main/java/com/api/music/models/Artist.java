package com.api.music.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Artist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Field name is required.")
  private String name;
  @Pattern(regexp = "https?:\\/\\/.*\\.(?:png|jpg)|(https://placehold.co/500)", message = "Invalid URL.")
  private String imageUrl = "https://placehold.co/500";
  @NotEmpty
      (message = "Field originCountry is required.")
  private String originCountry;
  @NotEmpty(message = "Field genre is required.")
  private String genre;

  public Artist(Long id, String name, String imageUrl, String originCountry, String genre) {
    this.id = id;
    this.name = name;
    Optional.ofNullable(imageUrl)
        .filter(Predicate.not(String::isBlank))
        .ifPresent(img -> this.imageUrl = img);
    this.originCountry = originCountry;
    this.genre = genre;
  }
}
