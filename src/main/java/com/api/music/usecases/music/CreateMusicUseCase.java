package com.api.music.usecases.music;

import com.api.music.models.Album;
import com.api.music.models.Music;
import com.api.music.repository.album.AlbumRepositoryPort;
import com.api.music.repository.music.MusicRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMusicUseCase {

    private final MusicRepositoryPort musicRepository;
    private final AlbumRepositoryPort albumRepository;

    public void createMusic(Music music) {
        Album album = music.getAlbum();
        album.setNumOfTracks(album.getNumOfTracks() + 1);
        album.setTotalDuration(album.getTotalDuration() + music.getDuration());
        albumRepository.save(album);
        try {
            musicRepository.save(music);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("You can't have two tracks with the same number in the same album.");
        }
    }
}
