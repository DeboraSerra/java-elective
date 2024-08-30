package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entites.Publisher;
import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.exception.PublisherNotFound;
import com.betrybe.alexandria.repository.PublisherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

  private final PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public Publisher findById(Long id) throws PublisherNotFound {
    return publisherRepository.findById(id).orElseThrow(PublisherNotFound::new);
  }

  public List<Publisher> findAll() {
    return publisherRepository.findAll();
  }

  public Publisher create(Publisher publisher) throws InvalidBody {
    if (publisher.getName() == null)
      throw new InvalidBody("Publisher name cannot be empty");
    if (publisher.getAddress() == null)
      throw new InvalidBody("Publisher address cannot be empty");
    return publisherRepository.save(publisher);
  }

  public Publisher update(Long id, Publisher publisher) throws PublisherNotFound, InvalidBody {
    Publisher publisherFromDb = findById(id);

    if (publisher.getName() == null)
      throw new InvalidBody("Publisher name cannot be empty");
    if (publisher.getAddress() == null)
      throw new InvalidBody("Publisher address cannot be empty");

    publisherFromDb.setName(publisher.getName());
    publisherFromDb.setAddress(publisher.getAddress());
    return publisherRepository.save(publisherFromDb);
  }

  public Publisher deleteById(Long id) throws PublisherNotFound {
    Publisher publisherFromDb = findById(id);
    publisherRepository.deleteById(id);
    return publisherFromDb;
  }
}
