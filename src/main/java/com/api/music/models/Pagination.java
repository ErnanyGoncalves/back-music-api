package com.api.music.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

  private Integer currentPage;
  private Integer itemsPerPage;
//  private Integer itemsInThePage;
//  private Long totalItems;
//  private Integer totalPages;
//  private Navigation navigationPaths;
}
