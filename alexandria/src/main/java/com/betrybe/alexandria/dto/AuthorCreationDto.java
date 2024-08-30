package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.Author;

public record AuthorCreationDto(String name, String nationality) {

  public Author toEntity() {
    return new Author(name, nationality);
  }
}
