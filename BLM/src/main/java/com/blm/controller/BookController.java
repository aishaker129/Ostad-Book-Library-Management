package com.blm.controller;

import com.blm.entity.Book;
import com.blm.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createBook = bookService.createBook(book);
        if (createBook == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updateBook = bookService.updateBook(id, book);
        if (updateBook == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String authorName) {
        List<Book> book = bookService.findBookByAuthor(authorName);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> book = bookService.findBookByGenre(genre);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/publication/{publication}")
    public ResponseEntity<List<Book>> getBooksByPublication(@PathVariable String publication) {
        List<Book> book = bookService.findBookByPublication(publication);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

}
