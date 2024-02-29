package com.api.music.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NavigationTest {
  @Test
  public void testNavigationConstructor() {

    String previousPage = "http://example.com/previous";
    String nextPage = "http://example.com/next";


    Navigation navigation = new Navigation(previousPage, nextPage);


    assertEquals(previousPage, navigation.getPreviousPage());
    assertEquals(nextPage, navigation.getNextPage());
  }

  @Test
  public void testNavigationSetterGetter() {

    String previousPage = "http://example.com/previous";
    String nextPage = "http://example.com/next";
    Navigation navigation = new Navigation();


    navigation.setPreviousPage(previousPage);
    navigation.setNextPage(nextPage);


    assertEquals(previousPage, navigation.getPreviousPage());
    assertEquals(nextPage, navigation.getNextPage());
  }

  @Test
  public void testNavigationNoArgsConstructor() {

    Navigation navigation = new Navigation();


    assertNull(navigation.getPreviousPage());
    assertNull(navigation.getNextPage());
  }
}