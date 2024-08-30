package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.Book;

public record BookDto(Long id, String title, String genre) {
  public static BookDto fromEntity(Book book) {
    return new BookDto(book.getId(), book.getTitle(), book.getGenre());
  }
}
