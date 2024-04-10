package com.api.music.utils;

import com.api.music.models.Navigation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilterUtils {

  public static Navigation buildNavigation(String endpoint, Integer currentPage, Integer totalPages,
      String filters) {
    Integer pPage = null;
    Integer cPage = currentPage;
    Integer nPage = null;

    if (cPage > 1) {
      pPage = cPage - 1;
    }
    if (cPage < totalPages) {
      nPage = cPage + 1;
    }

    String pPageStr = null;
    String cPageStr = String.format("/%s?page=%s%s", endpoint, cPage, filters);
    String nPageStr = null;

    if (pPage != null) {
      pPageStr = "/" + endpoint + "?page=" + pPage + filters;
    }
    if (nPage != null) {
      nPageStr = "/" + endpoint + "?page=" + nPage + filters;
    }
    return new Navigation(pPageStr, cPageStr, nPageStr);
  }
}
