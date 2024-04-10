package com.api.music.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.api.music.models.Navigation;
import org.junit.jupiter.api.Test;

class FilterUtilsTest {

  @Test
  public void testBuildNavigation() {
    String endpoint = "example";
    Integer currentPage = 2;
    Integer totalPages = 5;
    String filters = "&filter1=value1&filter2=value2";

    Navigation navigation = FilterUtils.buildNavigation(endpoint, currentPage, totalPages, filters);

    assertEquals("/example?page=1&filter1=value1&filter2=value2", navigation.getPreviousPage());
    assertEquals("/example?page=2&filter1=value1&filter2=value2", navigation.getCurrentPage());
    assertEquals("/example?page=3&filter1=value1&filter2=value2", navigation.getNextPage());
  }

  @Test
  public void testBuildNavigation2() {
    String endpoint = "example";
    Integer currentPage = 2;
    Integer totalPages = 2;
    String filters = "&filter1=value1&filter2=value2";

    Navigation navigation = FilterUtils.buildNavigation(endpoint, currentPage, totalPages, filters);

    assertEquals("/example?page=1&filter1=value1&filter2=value2", navigation.getPreviousPage());
    assertEquals("/example?page=2&filter1=value1&filter2=value2", navigation.getCurrentPage());
    assertEquals(null, navigation.getNextPage());
  }

  @Test
  public void testBuildNavigation3() {
    String endpoint = "example";
    Integer currentPage = 1;
    Integer totalPages = 5;
    String filters = "&filter1=value1&filter2=value2";

    Navigation navigation = FilterUtils.buildNavigation(endpoint, currentPage, totalPages, filters);

    assertEquals(null, navigation.getPreviousPage());
    assertEquals("/example?page=1&filter1=value1&filter2=value2", navigation.getCurrentPage());
    assertEquals("/example?page=2&filter1=value1&filter2=value2", navigation.getNextPage());
  }
}