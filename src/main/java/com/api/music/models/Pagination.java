package com.api.music.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {

  private Integer currentPage;
  private Integer itemsPerPage;
  private Long totalItems;
  private Integer totalPages;
  private Navigation navigationPaths;

  public Pagination(Integer currentPage, Integer itemsPerPage) {
    this.currentPage = currentPage;
    this.itemsPerPage = itemsPerPage;
  }
}
