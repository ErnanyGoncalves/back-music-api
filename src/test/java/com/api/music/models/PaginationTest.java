package com.api.music.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaginationTest {
  @Test
  public void testPaginationConstructor() {

    Integer currentPage = 1;
    Integer itemsPerPage = 10;


    Pagination pagination = new Pagination(currentPage, itemsPerPage);


    assertEquals(currentPage, pagination.getCurrentPage());
    assertEquals(itemsPerPage, pagination.getItemsPerPage());
    assertNull(pagination.getTotalItems());
    assertNull(pagination.getTotalPages());
    assertNull(pagination.getNavigationPaths());
  }

  @Test
  public void testPaginationSetterGetter() {

    Integer currentPage = 1;
    Integer itemsPerPage = 10;
    Long totalItems = 100L;
    Integer totalPages = 10;
    Navigation navigationPaths = new Navigation("http://example.com/previous", "http://example.com/next");
    Pagination pagination = new Pagination(currentPage, itemsPerPage);


    pagination.setTotalItems(totalItems);
    pagination.setTotalPages(totalPages);
    pagination.setNavigationPaths(navigationPaths);


    assertEquals(totalItems, pagination.getTotalItems());
    assertEquals(totalPages, pagination.getTotalPages());
    assertEquals(navigationPaths, pagination.getNavigationPaths());
  }
}