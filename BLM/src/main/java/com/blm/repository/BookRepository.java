package com.blm.repository;

import com.blm.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByAuthorContainingIgnoreCase(String author);

    List<Book> findAllBooksByPublicationContainingIgnoreCase(String publication);

    List<Book> findBooksByGenreContainingIgnoreCase(String genre);
}
