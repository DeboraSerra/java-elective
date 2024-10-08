package com.betrybe.alexandria.repository;

import com.betrybe.alexandria.entites.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

}
