package com.api.music.repository.artist;

import com.api.music.models.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Page<Artist> findByOriginCountryInAndGenreIn(List<String> originCountries, List<String> genres, Pageable pageable);

}
