package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ArtistTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  public void testConstructorArtist() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Set<ConstraintViolation<Artist>> violations = validator.validate(artist);

    assertEquals(0, violations.size());
    assertEquals(1L, artist.getId());
    assertEquals("Lady Gaga", artist.getName());

    artist.setImageUrl("https://placehold.co/500");
    assertEquals("https://placehold.co/500", artist.getImageUrl());
    assertEquals("United States", artist.getOriginCountry());
    assertEquals("Pop", artist.getGenre());
  }

  @Test
  public void testConstructorArtistErrors() {
    Artist artist = new Artist();
    Set<ConstraintViolation<Artist>> violations = validator.validate(artist);

    assertEquals(4, violations.size());

    artistValidationMessages(violations);
  }

  @Test
  public void testConstructorArtistErrors2() {
    Artist artist = new Artist(1L, "Lady Gaga", "http://www.google.com", "United States", "Pop");
    Set<ConstraintViolation<Artist>> violations = validator.validate(artist);
    assertEquals(1, violations.size());

    artistValidationMessages(violations);
  }

  private void artistValidationMessages(Set<ConstraintViolation<Artist>> violations) {
    for (ConstraintViolation<Artist> violation : violations) {
      switch (violation.getPropertyPath().toString()) {
        case "name":
          assertEquals("Field name is required.", violation.getMessage());
          break;
        case "imageUrl":
          assertEquals("Invalid URL.", violation.getMessage());
          break;
        case "originCountry":
          assertEquals("Field originCountry is required.", violation.getMessage());
          break;
        case "genre":
          assertEquals("Field genre is required.", violation.getMessage());
          break;
      }
    }
  }

}