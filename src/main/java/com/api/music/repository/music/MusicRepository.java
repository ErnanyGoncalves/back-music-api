package com.api.music.repository.music;

import com.api.music.models.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long> {

    Page<Music> findByAlbumInAndArtistIn (List<String> albums, List<String> artists, Pageable pageable);

}
