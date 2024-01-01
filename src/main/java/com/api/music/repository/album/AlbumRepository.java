package com.api.music.repository.album;

import com.api.music.models.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Page<Album> findByYearInAndArtistIn(List<Integer> years, List<String> artists, Pageable pageable);

}
