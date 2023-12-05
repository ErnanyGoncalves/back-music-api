package com.api.music.mocks;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.models.Music;
import com.api.music.repository.music.MusicRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class MusicDataInitializer implements CommandLineRunner {

  private final MusicRepository musicRepository;

  @Autowired
  public MusicDataInitializer(MusicRepository musicRepository) {
    this.musicRepository = musicRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Artist> artists = Arrays.asList(
        new Artist("Armin Van Buuren", "https://placehold.co/500", "netherlands", "electronic"),
        new Artist("TWICE", "https://placehold.co/500", "south korea", "k-pop"),
        new Artist("Ed Sheeran", "https://placehold.co/500", "uk", "pop"),
        new Artist("Lady Gaga", "https://placehold.co/500", "usa", "pop"),
        new Artist("Epica", "https://placehold.co/500", "netherlands", "metal"),
        new Artist("David Guetta", "https://placehold.co/500", "france", "electronic"),
        );

    List<Album> albums = Arrays.asList(
        new Album("Feel Again","https://placehold.co/500",2023,99,9999,artists.get(0)),
        new Album("Embrace","https://placehold.co/500",2015,99,9999,artists.get(0)),
        new Album("READY TO BE","https://placehold.co/500",2023,99,9999,artists.get(1)),
        new Album("Perfect World","https://placehold.co/500",2021,99,9999,artists.get(1)),
        new Album("=","https://placehold.co/500",2021,99,9999,artists.get(2)),
        new Album("+","https://placehold.co/500",2011,99,9999,artists.get(2)),
        new Album("Chromatica","https://placehold.co/500",2020,99,9999,artists.get(3)),
        new Album("Joanne","https://placehold.co/500",2016,99,9999,artists.get(3)),
        new Album("Omega","https://placehold.co/500",2021,99,9999,artists.get(4)),
        new Album("Design Your Universe","https://placehold.co/500",2009,99,9999,artists.get(4)),
        new Album("Nothing but the Beat","https://placehold.co/500",2012,99,9999,artists.get(5)),
        new Album("One More Love","https://placehold.co/500",2010,99,9999,artists.get(5))
    );


    if (musicRepository.count() == 0) {
      List<Music> musics = Arrays.asList(
          new Music("Feel Again", 1, 99, albums.get(0), artists.get(0)),
          new Music("Oumuamua", 2, 99, albums.get(0), artists.get(0)),
          new Music("Embrace", 1, 99, albums.get(1), artists.get(0)),
          new Music("Another You", 2, 99, albums.get(1), artists.get(0)),
          new Music("SET ME FREE", 1, 99, albums.get(2), artists.get(1)),
          new Music("MOONLIGHT SUNRISE", 2, 99, albums.get(2), artists.get(1)),
          new Music("Perfect World", 1, 99, albums.get(3), artists.get(1)),
          new Music("Better", 2, 99, albums.get(3), artists.get(1)),
          new Music("Tides", 1, 99, albums.get(4), artists.get(2)),
          new Music("Shivers", 2, 99, albums.get(4), artists.get(2)),
          new Music("The A Team", 1, 99, albums.get(5), artists.get(2)),
          new Music("Drunk", 2, 99, albums.get(5), artists.get(2)),
          new Music("Chromatica I", 1, 99, albums.get(6), artists.get(3)),
          new Music("Alice", 2, 99, albums.get(6), artists.get(3)),
          new Music("Diamond Heart", 1, 99, albums.get(7), artists.get(3)),
          new Music("AY-O", 2, 99, albums.get(7), artists.get(3)),
          new Music("Alpha - Anteludium -", 1, 99, albums.get(8), artists.get(4)),
          new Music("Abyss of Time - Countdown to Singularity -", 2, 99, albums.get(8), artists.get(4)),
          new Music("Samadhi - Prelude", 1, 99, albums.get(9), artists.get(4)),
          new Music("Resign to Surrender - A New Age Dawns, Pt. 4", 2, 99, albums.get(9), artists.get(4)),
          new Music("Titanium", 1, 99, albums.get(10), artists.get(5)),
          new Music("Turn Me On", 2, 99, albums.get(10), artists.get(5)),
          new Music("When Love Takes Over", 1, 99, albums.get(11), artists.get(5)),
          new Music("Gettin' Over", 2, 99, albums.get(11), artists.get(5))

      );

      musicRepository.saveAll(musics);
    }
  }
}
