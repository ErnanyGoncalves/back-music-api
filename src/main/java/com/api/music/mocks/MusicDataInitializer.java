package com.api.music.mocks;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.models.Music;
import com.api.music.repository.album.AlbumRepository;
import com.api.music.repository.artist.ArtistRepository;
import com.api.music.repository.music.MusicRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class MusicDataInitializer implements CommandLineRunner {

  private final MusicRepository musicRepository;
  private final ArtistRepository artistRepository;
  private final AlbumRepository albumRepository;


  @Autowired
  public MusicDataInitializer(MusicRepository musicRepository, ArtistRepository artistRepository,
      AlbumRepository albumRepository) {
    this.musicRepository = musicRepository;

    this.artistRepository = artistRepository;

    this.albumRepository = albumRepository;
  }

  private List<Music> getInitialMusics() {
    List<Artist> artists = artistRepository.findAll();

    List<Album> albums = albumRepository.findAll();

    return Arrays.asList(
        new Music(null, "Feel Again", 1, 99, albums.get(0), artists.get(0)),
        new Music(null, "Oumuamua", 2, 99, albums.get(0), artists.get(0)),
        new Music(null, "Embrace", 1, 99, albums.get(1), artists.get(0)),
        new Music(null, "Another You", 2, 99, albums.get(1), artists.get(0)),
        new Music(null, "SET ME FREE", 1, 99, albums.get(2), artists.get(1)),
        new Music(null, "MOONLIGHT SUNRISE", 2, 99, albums.get(2), artists.get(1)),
        new Music(null, "Perfect World", 1, 99, albums.get(3), artists.get(1)),
        new Music(null, "Better", 2, 99, albums.get(3), artists.get(1)),
        new Music(null, "Tides", 1, 99, albums.get(4), artists.get(2)),
        new Music(null, "Shivers", 2, 99, albums.get(4), artists.get(2)),
        new Music(null, "The A Team", 1, 99, albums.get(5), artists.get(2)),
        new Music(null, "Drunk", 2, 99, albums.get(5), artists.get(2)),
        new Music(null, "Chromatica I", 1, 99, albums.get(6), artists.get(3)),
        new Music(null, "Alice", 2, 99, albums.get(6), artists.get(3)),
        new Music(null, "Diamond Heart", 1, 99, albums.get(7), artists.get(3)),
        new Music(null, "AY-O", 2, 99, albums.get(7), artists.get(3)),
        new Music(null, "Alpha - Anteludium -", 1, 99, albums.get(8), artists.get(4)),
        new Music(null, "Abyss of Time - Countdown to Singularity -", 2, 99, albums.get(8),
            artists.get(4)),
        new Music(null, "Samadhi - Prelude", 1, 99, albums.get(9), artists.get(4)),
        new Music(null, "Resign to Surrender - A New Age Dawns, Pt. 4", 2, 99, albums.get(9),
            artists.get(4)),
        new Music(null, "Titanium", 1, 99, albums.get(10), artists.get(5)),
        new Music(null, "Turn Me On", 2, 99, albums.get(10), artists.get(5)),
        new Music(null, "When Love Takes Over", 1, 99, albums.get(11), artists.get(5)),
        new Music(null, "Gettin' Over", 2, 99, albums.get(11), artists.get(5))

    );
  }


  @Override
  public void run(String... args) throws Exception {

    if (musicRepository.count() == 0) {
      List<Music> musics = getInitialMusics();
      musicRepository.saveAll(musics);
    }
  }
}
