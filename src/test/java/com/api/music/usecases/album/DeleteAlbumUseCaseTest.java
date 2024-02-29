package com.api.music.usecases.album;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.repository.album.AlbumRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DeleteAlbumUseCaseTest {
  @Mock
  private AlbumRepositoryPort albumRepository;

  @InjectMocks
  private DeleteAlbumUseCase deleteAlbumUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDeleteAlbum() {

    Long albumId = 1L;

    deleteAlbumUseCase.deleteAlbum(albumId);
    Mockito.verify(albumRepository, Mockito.times(1)).delete(albumId);
  }
}