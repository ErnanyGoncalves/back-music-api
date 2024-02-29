package com.api.music.usecases.music;

import static org.junit.jupiter.api.Assertions.*;

import com.api.music.repository.music.MusicRepositoryPort;
import com.api.music.usecases.music.DeleteMusicUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DeleteMusicUseCaseTest {
  @Mock
  private MusicRepositoryPort musicRepository;

  @InjectMocks
  private DeleteMusicUseCase deleteMusicUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDeleteMusic() {

    Long musicId = 1L;

    deleteMusicUseCase.deleteMusic(musicId);
    Mockito.verify(musicRepository, Mockito.times(1)).delete(musicId);
  }
}