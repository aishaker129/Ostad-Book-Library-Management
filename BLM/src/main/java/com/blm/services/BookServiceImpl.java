package com.blm.services;

import com.blm.entity.Book;
import com.blm.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    @Override
    public Book createBook(Book book) {
        Book createBook = new Book();
        createBook.setId(book.getId());
        createBook.setTitle(book.getTitle());
        createBook.setAuthor(book.getAuthor());
        createBook.setPublication(book.getPublication());
        createBook.setPublicationYear(book.getPublicationYear());
        createBook.setAvailableCopies(book.getAvailableCopies());
        createBook.setGenre(book.getGenre());
        return bookRepository.save(createBook);
    }

    @Override
    public Book updateBook(Long id,Book book) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book updatedBook = oldBook.get();
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPublication(book.getPublication());
            updatedBook.setPublicationYear(book.getPublicationYear());
            updatedBook.setAvailableCopies(book.getAvailableCopies());
            updatedBook.setGenre(book.getGenre());
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    @Override
    public String deleteBookById(Long id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            bookRepository.deleteById(id);
            return "Book deleted successfully";
        }
        return "Book not found";
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> book = bookRepository.findBookByAuthorContainingIgnoreCase(author);
        return book;
    }

    @Override
    public List<Book> findBookByPublication(String publication) {
        List<Book> books = bookRepository.findAllBooksByPublicationContainingIgnoreCase(publication);
        return books;
    }

    @Override
    public List<Book> findBookByGenre(String genre) {
        List<Book> books = bookRepository.findBooksByGenreContainingIgnoreCase(genre);
        return books;
    }
}
