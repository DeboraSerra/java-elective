package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.Author;
import com.betrybe.alexandria.entites.Book;

public record AuthorDto(Long id, String name, String nationality) {

  public static AuthorDto fromEntity(Author author) {
    return new AuthorDto(author.getId(), author.getName(), author.getNationality());
  }

}
