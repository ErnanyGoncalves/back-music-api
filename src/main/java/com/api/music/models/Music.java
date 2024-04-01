package com.api.music.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints =
    {
        @UniqueConstraint(name = "UniqueTrackNumAndAlbumId", columnNames = {"trackNum",
            "album_id"})})
public class Music {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Field title is required.")
  private String title;
  @Min(value = 1, message = "The trackNum has to be greater than 0.")
  @NotNull(message = "Field trackNum is required.")
  private Integer trackNum;
  @Min(value = 1, message = "The duration has to be greater than 0.")
  @NotNull(message = "Field duration is required.")
  private Integer duration;
  @ManyToOne
  @JoinColumn(name = "album_id")
  @NotNull(message = "The music must have an album.")
  private Album album;
  @ManyToOne
  @JoinColumn(name = "artist_id")
  @NotNull(message = "The music must have an artist.")
  private Artist artist;


}
