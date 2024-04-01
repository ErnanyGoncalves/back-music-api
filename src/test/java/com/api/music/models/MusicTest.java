package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MusicTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  public void testConstructorMusic() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);
    Music music = new Music(1L, "Marry the Night", 1, 265000, album, artist);

    Set<ConstraintViolation<Music>> violations = validator.validate(music);

    assertEquals(0, violations.size());

    assertEquals(1L, music.getId());
    assertEquals("Marry the Night", music.getTitle());

    assertEquals(1, music.getTrackNum());
    assertEquals(265000, music.getDuration());
    assertEquals(album, music.getAlbum());
    assertEquals(artist, music.getArtist());

  }

  @Test
  public void testConstructorMusicErrors() {
    Music music = new Music();
    Set<ConstraintViolation<Music>> violations = validator.validate(music);

    assertEquals(5, violations.size());

    musicValidationMessages(violations);

  }

  @Test
  public void testConstructorMusicErrors2() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);
    Music music = new Music(1L, "Marry the Night", 0, 0, album, artist);
    Set<ConstraintViolation<Music>> violations = validator.validate(music);

    assertEquals(2, violations.size());

    musicValidationMessages(violations);
  }

  private void musicValidationMessages(Set<ConstraintViolation<Music>> violations) {
    for (ConstraintViolation<Music> violation : violations) {
      Boolean minViolationInfoExists =
          violation.getConstraintDescriptor().getAnnotation().toString().indexOf("constraints.Min")
              != -1;
      Boolean notNullViolationInfoExists =
          violation.getConstraintDescriptor().getAnnotation().toString()
              .indexOf("constraints.NotNull") != -1;

      switch (violation.getPropertyPath().toString()) {
        case "title":
          assertEquals("Field title is required.", violation.getMessage());
          break;
        case "trackNum":
          if (minViolationInfoExists) {
            assertEquals("The trackNum has to be greater than 0.", violation.getMessage());
          }
          if (notNullViolationInfoExists) {
            assertEquals("Field trackNum is required.", violation.getMessage());
          }
          break;
        case "duration":
          if (minViolationInfoExists) {
            assertEquals("The duration has to be greater than 0.", violation.getMessage());
          }
          if (notNullViolationInfoExists) {
            assertEquals("Field duration is required.", violation.getMessage());
          }
          break;
        case "artist":
          assertEquals("The music must have an artist.", violation.getMessage());
          break;
        case "album":
          assertEquals("The music must have an album.", violation.getMessage());
          break;
      }
    }
  }

  @Test
  public void testToString() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);
    Music music = new Music(1L, "Marry the Night", 1, 265000, album, artist);

    String expectedToString = "Music(id=1, title=Marry the Night, trackNum=1, duration=265000, album=Album(id=1, title=Born This Way, imageUrl=null, year=2011, numOfTracks=0, totalDuration=0, artist=Artist(id=1, name=Lady Gaga, imageUrl=null, originCountry=United States, genre=Pop)), artist=Artist(id=1, name=Lady Gaga, imageUrl=null, originCountry=United States, genre=Pop))";
    assertEquals(expectedToString, music.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    Artist artist1 = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Artist artist2 = new Artist(2L, "Beyoncé", null, "United States", "Pop");
    Album album1 = new Album(1L, "Born This Way", null, 2011, artist1);
    Album album2 = new Album(2L, "Lemonade", null, 2016, artist2);

    Music music1 = new Music(1L, "Marry the Night", 1, 265000, album1, artist1);
    Music music2 = new Music(1L, "Marry the Night", 1, 265000, album1, artist1);
    Music music3 = new Music(2L, "Halo", 1, 240000, album2, artist2);


    assertEquals(music1, music2);
    assertNotEquals(music1, music3);


    assertEquals(music1.hashCode(), music2.hashCode());
    assertNotEquals(music1.hashCode(), music3.hashCode());
  }
}