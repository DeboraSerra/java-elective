package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.dto.BookCreationDto;
import com.betrybe.alexandria.dto.BookDetailCreationDto;
import com.betrybe.alexandria.dto.BookDetailDto;
import com.betrybe.alexandria.dto.BookDto;
import com.betrybe.alexandria.entites.Book;
import com.betrybe.alexandria.entites.BookDetail;
import com.betrybe.alexandria.exception.BookDetailNotFound;
import com.betrybe.alexandria.exception.BookNotFound;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.exception.NotFound;
import com.betrybe.alexandria.service.BookService;
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
@RequestMapping(value = "/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getById(@PathVariable Long id) throws BookNotFound {
    return ResponseEntity.status(200).body(BookDto.fromEntity(bookService.findById(id)));
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getAll() {
    List<Book> books = bookService.findAll();
    List<BookDto> parsedBooks = books.stream().map(BookDto::fromEntity).toList();
    return ResponseEntity.status((200)).body(parsedBooks);
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@RequestBody BookCreationDto body) throws InvalidBody {
    Book book = bookService.create(body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(BookDto.fromEntity(book));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookCreationDto body)
      throws BookNotFound, InvalidBody {
    Book book = bookService.update(id, body.toEntity());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDto> delete(@PathVariable Long id) throws BookNotFound {
    Book book = bookService.deleteById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }

  @PostMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> createBookDetail(@PathVariable Long bookId, @RequestBody
  BookDetailCreationDto body) throws BookNotFound, InvalidBody {
    BookDetail bookDetail = bookService.createBookDetail(bookId, body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(BookDetailDto.fromEntity(bookDetail));
  }

  @PutMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> updateBookDetail(@PathVariable Long bookId, @RequestBody
  BookDetailCreationDto body) throws BookNotFound, BookDetailNotFound, InvalidBody {
    BookDetail bookDetail = bookService.updateBookDetail(bookId, body.toEntity());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDetailDto.fromEntity(bookDetail));
  }

  @GetMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> getBookDetail(@PathVariable Long bookId)
      throws BookNotFound, BookDetailNotFound {
    BookDetail bookDetail = bookService.getBookDetail(bookId);
    return ResponseEntity.status(HttpStatus.OK).body(BookDetailDto.fromEntity(bookDetail));
  }

  @DeleteMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> deleteBookDetail(@PathVariable Long bookId)
      throws BookNotFound, BookDetailNotFound {
    BookDetail bookDetail = bookService.deleteBookDetail(bookId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDetailDto.fromEntity(bookDetail));
  }

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public ResponseEntity<BookDto> setBookPublisher(@PathVariable Long bookId,
      @PathVariable Long publisherId)
      throws NotFound {
    Book book = bookService.setBookPublisher(bookId, publisherId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }

  @DeleteMapping("/{bookId}/publisher")
  public ResponseEntity<BookDto> removeBookPublisher(@PathVariable Long bookId)
      throws BookNotFound {
    Book book = bookService.removeBookPublisher(bookId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }

  @PutMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<BookDto> addBookAuthor(@PathVariable Long bookId,
      @PathVariable Long authorId) throws NotFound {
    Book book = bookService.addBookAuthor(bookId, authorId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }

  @DeleteMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<BookDto> removeBookAuthor(@PathVariable Long bookId,
      @PathVariable Long authorId) throws NotFound {
    Book book = bookService.removeBookAuthor(bookId, authorId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(BookDto.fromEntity(book));
  }
}
