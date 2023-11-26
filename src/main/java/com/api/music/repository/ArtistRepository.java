package com.api.music.repository;

import com.api.music.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist,Long> {

    List<Artist> findByCountryInAndGenreIn (List<String> originCountries, List<String> genres);

}
