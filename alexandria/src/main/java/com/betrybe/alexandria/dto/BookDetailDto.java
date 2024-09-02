package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.BookDetail;

public record BookDetailDto(Long id,
                            String summary,
                            Integer pageCount,
                            String year,
                            String isbn) {

  public static BookDetailDto fromEntity(BookDetail bookDetail) {
    return new BookDetailDto(bookDetail.getId(), bookDetail.getSummary(), bookDetail.getPageCount(),
        bookDetail.getYear(), bookDetail.getIsbn());
  }
}
