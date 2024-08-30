package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entites.Author;
import com.betrybe.alexandria.exception.AuthorNotFound;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author findById(Long id) throws AuthorNotFound {
    return authorRepository.findById(id).orElseThrow(AuthorNotFound::new);
  }

  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  public Author create(Author author) throws InvalidBody {
    if(author.getName() == null) throw new InvalidBody("Author name cannot be empty");
    if (author.getNationality() == null)
      throw new InvalidBody("Author nationality cannot be empty");
    return authorRepository.save(author);
  }

  public Author update(Long id, Author author) throws AuthorNotFound, InvalidBody {
    Author authorFromDb = findById(id);
    if (author.getName() == null)
      throw new InvalidBody("Author name cannot be empty");
    if (author.getNationality() == null)
      throw new InvalidBody("Author nationality cannot be empty");

    authorFromDb.setName(author.getName());
    authorFromDb.setNationality(author.getNationality());
    return authorRepository.save(authorFromDb);
  }

  public Author deleteById(Long id) throws AuthorNotFound {
    Author authorFromDb = findById(id);
    authorRepository.deleteById(id);
    return authorFromDb;
  }
}
