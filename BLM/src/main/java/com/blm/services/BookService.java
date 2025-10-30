package com.blm.services;

import com.blm.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    String deleteBookById(Long id);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByPublication(String publication);
    List<Book> findBookByGenre(String genre);
}
