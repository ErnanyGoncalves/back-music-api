package com.api.music.mocks;

import com.api.music.models.Album;
import com.api.music.models.Artist;
import com.api.music.repository.album.AlbumRepository;
import com.api.music.repository.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AlbumDataInitializer implements CommandLineRunner {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumDataInitializer(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    private List<Album> getInitialAlbums() {
        List<Artist> artists = artistRepository.findAll();

        return Arrays.asList(
            new Album(null,"Feel Again","https://placehold.co/500",2023,artists.get(0)),
            new Album(null,"Embrace","https://placehold.co/500",2015,artists.get(0)),
            new Album(null,"READY TO BE","https://placehold.co/500",2023,artists.get(1)),
            new Album(null,"Perfect World","https://placehold.co/500",2021,artists.get(1)),
            new Album(null,"=","https://placehold.co/500",2021,artists.get(2)),
            new Album(null,"+","https://placehold.co/500",2011,artists.get(2)),
            new Album(null,"Chromatica","https://placehold.co/500",2020,artists.get(3)),
            new Album(null,"Joanne","https://placehold.co/500",2016,artists.get(3)),
            new Album(null,"Omega","https://placehold.co/500",2021,artists.get(4)),
            new Album(null,"Design Your Universe","https://placehold.co/500",2009,artists.get(4)),
            new Album(null,"Nothing but the Beat","https://placehold.co/500",2012,artists.get(5)),
            new Album(null,"One More Love","https://placehold.co/500",2010,artists.get(5))
        );
    }

    @Override
    public void run(String... args) throws Exception {

        if (albumRepository.count() == 0) {
            List<Album> albums = getInitialAlbums();
            albumRepository.saveAll(albums);
        }
    }
}
