package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AlbumTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  public void testConstructorAlbum() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", null, 2011, artist);

    Set<ConstraintViolation<Album>> violations = validator.validate(album);

    assertEquals(0, violations.size());

    assertEquals(1L, album.getId());
    assertEquals("Born This Way", album.getTitle());
    // PQ EU TENHO Q SETAR 1o A URL pra poder verificar? O valor null não colocaria automaticamente?
    album.setImageUrl("https://placehold.co/500");
    assertEquals("https://placehold.co/500", album.getImageUrl());
    assertEquals(2011, album.getYear());
    assertEquals(artist, album.getArtist());


  }

  @Test
  public void testConstructorAlbumErrors() {

    Album album = new Album();
    Set<ConstraintViolation<Album>> violations = validator.validate(album);

    assertEquals(4, violations.size());
    albumValidationMessages(violations);

  }

  @Test
  public void testConstructorAlbumErrors2() {
    Artist artist = new Artist(1L, "Lady Gaga", null, "United States", "Pop");
    Album album = new Album(1L, "Born This Way", "http://www.google.com", 0, artist);
    Set<ConstraintViolation<Album>> violations = validator.validate(album);

    assertEquals(2, violations.size());

    albumValidationMessages(violations);
  }

  private void albumValidationMessages(Set<ConstraintViolation<Album>> violations) {

    for (ConstraintViolation<Album> violation : violations) {

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
        case "imageUrl":
          assertEquals("Invalid URL.", violation.getMessage());
          break;
        case "year":
          if (minViolationInfoExists) {
            assertEquals("The year has to be greater than 0.", violation.getMessage());
          }
          if (notNullViolationInfoExists) {
            assertEquals("Field year is required.", violation.getMessage());
          }
          break;
        case "artist":
          assertEquals("The album must have an artist.", violation.getMessage());
          break;
      }
    }
  }
}