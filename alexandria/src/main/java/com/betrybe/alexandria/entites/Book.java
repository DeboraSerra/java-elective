package com.betrybe.alexandria.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String genre;

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  private BookDetail bookDetail;

  @ManyToOne // muitos livros para uma editora
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  public Book() {
  }

  public Book(String title, String genre) {
    this.title = title;
    this.genre = genre;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public BookDetail getBookDetail() {
    return bookDetail;
  }

  public void setBookDetail(BookDetail bookDetail) {
    this.bookDetail = bookDetail;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
