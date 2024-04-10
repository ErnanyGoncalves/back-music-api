package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaginationTest {

  @Test
  void testPaginationConstructor() {

    Integer currentPage = 1;
    Integer itemsPerPage = 10;
    Long totalItems = 100L;
    Integer totalPages = 10;
    Navigation navigationPaths = new Navigation("previous", "current", "next");
    Pagination pagination = new Pagination(currentPage, itemsPerPage, totalItems, totalPages,
        navigationPaths);

    assertEquals(currentPage, pagination.getCurrentPage());
    assertEquals(itemsPerPage, pagination.getItemsPerPage());
    assertEquals(totalItems, pagination.getTotalItems());
    assertEquals(totalPages, pagination.getTotalPages());
    assertEquals(navigationPaths, pagination.getNavigationPaths());
  }


}