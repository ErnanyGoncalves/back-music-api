package com.api.music.dtos.common;

import com.api.music.models.Pagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor()
public class ResponseListDTO<T> {

  private final List<T> listOfData;
  private final Pagination pagination;
}
