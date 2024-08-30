package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.Book;
import com.betrybe.alexandria.entites.Publisher;

public record PublisherDto(Long id, String address, String name) {

  public static PublisherDto fromEntity(Publisher publisher) {
    return new PublisherDto(publisher.getId(), publisher.getAddress(), publisher.getName());
  }
}
