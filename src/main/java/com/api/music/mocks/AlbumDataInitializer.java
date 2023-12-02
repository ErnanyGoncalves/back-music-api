package com.api.music.mocks;

import com.api.music.models.Album;
import com.api.music.repository.album.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class AlbumDataInitializer implements CommandLineRunner {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumDataInitializer(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (albumRepository.count() == 0) {
            List<Album> albums = Arrays.asList(
                    new Album("Feel Again","https://placehold.co/500",2023,99,9999,1),
                    new Album("Embrace","https://placehold.co/500",2015,99,9999,1),
                    new Album("READY TO BE","https://placehold.co/500",2023,99,9999,2),
                    new Album("Perfect World","https://placehold.co/500",2021,99,9999,2),
                    new Album("=","https://placehold.co/500",2021,99,9999,3),
                    new Album("+","https://placehold.co/500",2011,99,9999,3),
                    new Album("Chromatica","https://placehold.co/500",2020,99,9999,4),
                    new Album("Joanne","https://placehold.co/500",2016,99,9999,4),
                    new Album("Omega","https://placehold.co/500",2021,99,9999,5),
                    new Album("Design Your Universe","https://placehold.co/500",2009,99,9999,5),
                    new Album("Nothing but the Beat","https://placehold.co/500",2012,99,9999,6),
                    new Album("One More Love","https://placehold.co/500",2010,99,9999,6)
            );

            albumRepository.saveAll(albums);
        }
    }
}
