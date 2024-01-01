package com.api.music.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseListDTO<T> {
    private final List<T> listOfData;
    private final Integer currentPage;
    private final Integer itemsPerPage;
}
