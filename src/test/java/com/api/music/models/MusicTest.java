package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void testConstructorMusic() {
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
  void testConstructorMusicErrors() {
    Music music = new Music();
    Set<ConstraintViolation<Music>> violations = validator.validate(music);

    assertEquals(5, violations.size());

    musicValidationMessages(violations);

  }

  @Test
  void testConstructorMusicErrors2() {
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
          violation.getConstraintDescriptor().getAnnotation().toString()
              .contains("constraints.Min");
      Boolean notNullViolationInfoExists =
          violation.getConstraintDescriptor().getAnnotation().toString()
              .contains("constraints.NotNull");

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
}