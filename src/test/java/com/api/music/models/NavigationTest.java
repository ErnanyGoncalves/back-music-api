package com.api.music.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NavigationTest {

  @Test
  void testNavigationConstructor() {
    Navigation navigation = new Navigation("previous", "current", "next");
    assertEquals("previous", navigation.getPreviousPage());
    assertEquals("current", navigation.getCurrentPage());
    assertEquals("next", navigation.getNextPage());
  }

  @Test
  void testNavigationSetterGetter() {
    Navigation navigation = new Navigation();
    navigation.setPreviousPage("previous");
    navigation.setCurrentPage("current");
    navigation.setNextPage("next");
    assertEquals("previous", navigation.getPreviousPage());
    assertEquals("current", navigation.getCurrentPage());
    assertEquals("next", navigation.getNextPage());
  }

  @Test
  void testNavigationNullFields() {
    Navigation navigation = new Navigation();
    assertNull(navigation.getPreviousPage());
    assertNull(navigation.getCurrentPage());
    assertNull(navigation.getNextPage());
  }

}