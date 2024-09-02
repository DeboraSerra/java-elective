package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.BookDetail;

public record BookDetailCreationDto(
    String summary,
    Integer pageCount,
    String year,
    String isbn) {

  public BookDetail toEntity() {
    return new BookDetail(summary, pageCount, year, isbn);
  }
}
