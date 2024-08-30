package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.dto.AuthorCreationDto;
import com.betrybe.alexandria.dto.AuthorDto;
import com.betrybe.alexandria.entites.Author;
import com.betrybe.alexandria.exception.AuthorNotFound;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDto> getById(@PathVariable Long id) throws AuthorNotFound {
    return ResponseEntity.status(200).body(AuthorDto.fromEntity(authorService.findById(id)));
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAll() {
    List<Author> authors = authorService.findAll();
    List<AuthorDto> parsedAuthors = authors.stream().map(AuthorDto::fromEntity).toList();
    return ResponseEntity.status((200)).body(parsedAuthors);
  }

  @PostMapping
  public ResponseEntity<AuthorDto> create(@RequestBody AuthorCreationDto body) throws InvalidBody {
    Author author = authorService.create(body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(AuthorDto.fromEntity(author));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody AuthorCreationDto body)
      throws AuthorNotFound, InvalidBody {
    Author author = authorService.update(id, body.toEntity());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(AuthorDto.fromEntity(author));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AuthorDto> delete(@PathVariable Long id) throws AuthorNotFound {
    Author author = authorService.deleteById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(AuthorDto.fromEntity(author));
  }
}
