package com.api.music.dtos.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseListDTO<T>{
  private final List<T> listOfData;
  private final Integer currentPage;
  private final Integer itemsPerPage;
}
