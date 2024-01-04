package com.api.music.mocks;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ArtistDataInitializer implements CommandLineRunner {

  private final ArtistRepository artistRepository;

  @Autowired
  public ArtistDataInitializer(ArtistRepository artistRepository) {
    this.artistRepository = artistRepository;
  }

  private List<Artist> getInitialArtists() {
    return Arrays.asList(
        new Artist(null, "Armin Van Buuren", "https://placehold.co/500", "netherlands",
            "electronic"),
        new Artist(null, "TWICE", "https://placehold.co/500", "south korea", "k-pop"),
        new Artist(null, "Ed Sheeran", "https://placehold.co/500", "uk", "pop"),
        new Artist(null, "Lady Gaga", "https://placehold.co/500", "usa", "pop"),
        new Artist(null, "Epica", "https://placehold.co/500", "netherlands", "metal"),
        new Artist(null, "David Guetta", "https://placehold.co/500", "france", "electronic")
    );
  }

  @Override
  public void run(String... args) throws Exception {
    if (artistRepository.count() == 0) {
      List<Artist> artists = getInitialArtists();
      artistRepository.saveAll(artists);
    }
  }
}
