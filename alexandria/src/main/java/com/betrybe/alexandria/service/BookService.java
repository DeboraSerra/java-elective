package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entites.Book;
import com.betrybe.alexandria.exception.BookNotFound;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book findById(Long id) throws BookNotFound {
    return bookRepository.findById(id).orElseThrow(BookNotFound::new);
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book create (Book book) throws InvalidBody {
    if (book.getGenre() == null) throw new InvalidBody("Book genre cannot be empty");
    if (book.getTitle() == null) throw new InvalidBody("Book title cannot be empty");
    return bookRepository.save(book);
  }

  public  Book update (Long id, Book book) throws BookNotFound, InvalidBody {
    Book bookFromDb = findById(id);
    if(book.getGenre() == null) throw new InvalidBody("Book genre cannot be empty");
    if (book.getTitle() == null)
      throw new InvalidBody("Book title cannot be empty");

    bookFromDb.setGenre(book.getGenre());
    bookFromDb.setTitle(book.getTitle());
    return bookRepository.save(bookFromDb);
  }

  public Book deleteById (Long id) throws BookNotFound {
    Book bookFromDb = findById(id);
    bookRepository.deleteById(id);
    return bookFromDb;
  }
}
