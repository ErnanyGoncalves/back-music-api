package com.api.music.adapters.api;

import com.api.music.dtos.common.ResponseListDTO;
import com.api.music.dtos.music.MusicDTO;
import com.api.music.models.Music;
import com.api.music.ports.MusicApiPort;
import com.api.music.usecases.music.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musics")
@AllArgsConstructor
public class MusicApiImpl implements MusicApiPort {

    private final GetMusicsUseCase getMusicsUseCase;
    private final GetMusicUseCase getMusicUseCase;
    private final CreateMusicUseCase createMusicUseCase;
    private final EditMusicUseCase editMusicUseCase;
    private final DeleteMusicUseCase deleteMusicUseCase;

//    @GetMapping
//    public ResponseListDTO<MusicDTO> getMusics(@RequestParam(name = "page", defaultValue = "1") Integer page,
//                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        List<MusicDTO> listOfMusics = getMusicsUseCase.getMusics(page - 1, pageSize);
//        return new ResponseListDTO<>(listOfMusics, page, pageSize);
//    }

    @GetMapping
    public ResponseListDTO<MusicDTO> getMusics(@RequestParam(name = "album", required= false) List<String> albums,
                                               @RequestParam(name = "artist", required= false) List<String> artists,
                                               @RequestParam(name = "page", defaultValue = "1") Integer page,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<MusicDTO> listOfMusics = getMusicsUseCase.getMusics(albums, artists, page - 1, pageSize);
        return new ResponseListDTO<>(listOfMusics, page, pageSize);
    }

    @GetMapping("/{id}")
    public MusicDTO getMusic(@PathVariable("id") Long id) {
        return getMusicUseCase.getMusic(id);

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMusic(@Valid @RequestBody Music music) {
        createMusicUseCase.createMusic(music);
    }

    @PutMapping("/{id}")
    public void editMusic(@PathVariable("id") Long id, @Valid @RequestBody Music newMusicData) {
        editMusicUseCase.editMusic(id, newMusicData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMusic(@PathVariable("id") Long id) {
        deleteMusicUseCase.deleteMusic(id);
    }
}
