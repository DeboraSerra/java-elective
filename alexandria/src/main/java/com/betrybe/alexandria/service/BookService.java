package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entites.Author;
import com.betrybe.alexandria.entites.Book;
import com.betrybe.alexandria.entites.BookDetail;
import com.betrybe.alexandria.entites.Publisher;
import com.betrybe.alexandria.exception.BookDetailNotFound;
import com.betrybe.alexandria.exception.BookNotFound;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.exception.NotFound;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final BookDetailRepository bookDetailRepository;
  private final PublisherService publisherService;
  private final AuthorService authorService;

  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository,
      PublisherService publisherService, AuthorService authorService) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherService = publisherService;
    this.authorService = authorService;
  }

  public Book findById(Long id) throws BookNotFound {
    return bookRepository.findById(id).orElseThrow(BookNotFound::new);
  }

  public List<Book> findAll(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Book> page = bookRepository.findAll(pageable);

    return page.toList();
  }

  public Book create(Book book) throws InvalidBody {
    if (book.getGenre() == null) {
      throw new InvalidBody("Book genre cannot be empty");
    }
    if (book.getTitle() == null) {
      throw new InvalidBody("Book title cannot be empty");
    }
    return bookRepository.save(book);
  }

  public Book update(Long id, Book book) throws BookNotFound, InvalidBody {
    Book bookFromDb = findById(id);
    if (book.getGenre() == null) {
      throw new InvalidBody("Book genre cannot be empty");
    }
    if (book.getTitle() == null) {
      throw new InvalidBody("Book title cannot be empty");
    }

    bookFromDb.setGenre(book.getGenre());
    bookFromDb.setTitle(book.getTitle());
    return bookRepository.save(bookFromDb);
  }

  public Book deleteById(Long id) throws BookNotFound {
    Book bookFromDb = findById(id);
    bookRepository.deleteById(id);
    return bookFromDb;
  }

  public BookDetail createBookDetail(Long bookId, BookDetail bookDetail)
      throws BookNotFound, InvalidBody {
    Book book = findById(bookId);
    if (bookDetail.getIsbn() == null || bookDetail.getSummary() == null
        || bookDetail.getYear() == null || bookDetail.getPageCount() == null) {
      throw new InvalidBody("Book detail is missing content");
    }
    bookDetail.setBook(book);
    return bookDetailRepository.save(bookDetail);
  }

  public BookDetail getBookDetail(Long bookId) throws BookNotFound, BookDetailNotFound {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getBookDetail();
    if (bookDetail == null) {
      throw new BookDetailNotFound();
    }
    return bookDetail;
  }

  public BookDetail updateBookDetail(Long bookId, BookDetail bookDetail)
      throws BookNotFound, InvalidBody, BookDetailNotFound {
    BookDetail bookDetailDb = getBookDetail(bookId);

    if (bookDetail.getIsbn() == null || bookDetail.getSummary() == null
        || bookDetail.getYear() == null || bookDetail.getPageCount() == null) {
      throw new InvalidBody("Book detail is missing content");
    }
    bookDetailDb.setIsbn(bookDetail.getIsbn());
    bookDetailDb.setYear(bookDetail.getYear());
    bookDetailDb.setSummary(bookDetail.getSummary());
    bookDetailDb.setPageCount(bookDetail.getPageCount());
    return bookDetailRepository.save(bookDetailDb);
  }

  public BookDetail deleteBookDetail(Long bookId) throws BookNotFound, BookDetailNotFound {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getBookDetail();
    if (bookDetail == null) {
      throw new BookDetailNotFound();
    }

    book.setBookDetail(null);
    bookDetail.setBook(null);

    bookDetailRepository.delete(bookDetail);
    return bookDetail;
  }

  public Book setBookPublisher(Long bookId, Long publisherId) throws NotFound {
    Book book = findById(bookId);
    Publisher publisher = publisherService.findById(publisherId);

    book.setPublisher(publisher);

    return bookRepository.save(book);
  }

  public Book removeBookPublisher(Long bookId) throws BookNotFound {
    Book book = findById(bookId);
    book.setPublisher(null);
    bookRepository.save(book);
    return book;
  }

  public Book addBookAuthor(Long bookId, Long authorId) throws NotFound {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);
    book.getAuthors().add(author);
    return bookRepository.save(book);
  }

  public Book removeBookAuthor(Long bookId, Long authorId) throws NotFound {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);
    book.getAuthors().remove(author);
    return bookRepository.save(book);
  }
}
