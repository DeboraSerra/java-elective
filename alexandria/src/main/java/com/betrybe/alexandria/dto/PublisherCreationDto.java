package com.betrybe.alexandria.dto;

import com.betrybe.alexandria.entites.Publisher;

public record PublisherCreationDto(String name, String address) {

  public Publisher toEntity() {
    return new Publisher(name, address);
  }
}
