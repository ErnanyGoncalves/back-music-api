package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class PaginationTest {

  @Test
  public void testPaginationConstructor() {

    Integer currentPage = 1;
    Integer itemsPerPage = 10;
    Long totalItems = 100L;
    Integer totalPages = 10;
    Navigation navigationPaths = new Navigation("previous","current","next");
    Pagination pagination = new Pagination(currentPage, itemsPerPage, totalItems, totalPages,navigationPaths);

    assertEquals(currentPage, pagination.getCurrentPage());
    assertEquals(itemsPerPage, pagination.getItemsPerPage());
    assertEquals(totalItems, pagination.getTotalItems());
    assertEquals(totalPages, pagination.getTotalPages());
    assertEquals(navigationPaths, pagination.getNavigationPaths());
  }

  @Test
  public void testToString() {
    Pagination pagination = new Pagination(1, 10, 100L, 10, new Navigation("previous", "current", "next"));
    String expectedToString = "Pagination(currentPage=1, itemsPerPage=10, totalItems=100, totalPages=10, navigationPaths=Navigation(previousPage=previous, currentPage=current, nextPage=next))";
    assertEquals(expectedToString, pagination.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    Pagination pagination1 = new Pagination(1, 10, 100L, 10, new Navigation("previous", "current", "next"));
    Pagination pagination2 = new Pagination(1, 10, 100L, 10, new Navigation("previous", "current", "next"));
    Pagination pagination3 = new Pagination(2, 20, 200L, 20, new Navigation("prev", "cur", "nxt"));

    assertEquals(pagination1, pagination2);
    assertNotEquals(pagination1, pagination3);
    assertEquals(pagination1.hashCode(), pagination2.hashCode());
    assertNotEquals(pagination1.hashCode(), pagination3.hashCode());
  }


}