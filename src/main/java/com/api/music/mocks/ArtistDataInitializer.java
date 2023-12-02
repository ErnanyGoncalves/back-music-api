package com.api.music.mocks;

import com.api.music.models.Artist;
import com.api.music.repository.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class ArtistDataInitializer implements CommandLineRunner {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistDataInitializer(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (artistRepository.count() == 0) {
            List<Artist> artists = Arrays.asList(
                    new Artist("Armin Van Buuren", "https://placehold.co/500", "netherlands", "electronic"),
                    new Artist("TWICE", "https://placehold.co/500", "south korea", "k-pop"),
                    new Artist("Ed Sheeran", "https://placehold.co/500", "uk", "pop"),
                    new Artist("Lady Gaga", "https://placehold.co/500", "usa", "pop"),
                    new Artist("Epica", "https://placehold.co/500", "netherlands", "metal"),
                    new Artist("David Guetta", "https://placehold.co/500", "france", "electronic"),
            );

            artistRepository.saveAll(artists);
        }
    }
}
