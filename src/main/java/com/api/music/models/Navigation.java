package com.api.music.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Navigation {
  private String previousPage;
  private String nextPage;
}
