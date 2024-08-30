package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.dto.AuthorDto;
import com.betrybe.alexandria.dto.PublisherCreationDto;
import com.betrybe.alexandria.dto.PublisherDto;
import com.betrybe.alexandria.entites.Publisher;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.exception.PublisherNotFound;
import com.betrybe.alexandria.service.PublisherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

  private final PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PublisherDto> getById(@PathVariable Long id) throws PublisherNotFound {
    return ResponseEntity.status(200).body(PublisherDto.fromEntity(publisherService.findById(id)));
  }

  @GetMapping
  public ResponseEntity<List<PublisherDto>> getAll() {
    List<Publisher> publishers = publisherService.findAll();
    List<PublisherDto> parsedPublishers = publishers.stream().map(PublisherDto::fromEntity).toList();
    return ResponseEntity.status((200)).body(parsedPublishers);
  }

  @PostMapping
  public ResponseEntity<PublisherDto> create(@RequestBody PublisherCreationDto body)
      throws InvalidBody {
    Publisher publisher = publisherService.create(body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(PublisherDto.fromEntity(publisher));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PublisherDto> update(@PathVariable Long id,
      @RequestBody PublisherCreationDto body)
      throws PublisherNotFound, InvalidBody {
    Publisher publisher = publisherService.update(id, body.toEntity());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(PublisherDto.fromEntity(publisher));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PublisherDto> delete(@PathVariable Long id) throws PublisherNotFound {
    Publisher publisher = publisherService.deleteById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(PublisherDto.fromEntity(publisher));
  }
}
