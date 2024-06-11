package lib.project.backend.controllers;

import lib.project.backend.model.dto.BookCreationDTO;
import lib.project.backend.model.entity.Book;
import lib.project.backend.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
        @RequestParam(required = false, name = "search") String searchQuery,
        @RequestParam(required = false, name = "show") String showQuery,
        @RequestParam(required = false, name = "page") Integer page) {
        List<Book> books;
        if (searchQuery != null && !searchQuery.isEmpty() || showQuery != null && !showQuery.isEmpty()) {
            books = bookService.findBooksByName(searchQuery, showQuery);
        } else {
            books = bookService.getAllBooks();
        }
        if (page != null && page > 0) {
            int booksPerPage = 8;
            int maxPages = (int)Math.ceil((double)books.size() / booksPerPage);
            int startIndex = (page - 1) * booksPerPage;
            int endIndex = Math.min(startIndex + booksPerPage, books.size());

            books = books.subList(startIndex, endIndex);

            return new ResponseEntity<>(books, (page == maxPages ? HttpStatus.ACCEPTED : HttpStatus.OK));
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookCreationDTO bookCreationDTO) {
        Book createdBook = bookService.createBook(bookCreationDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedProject = bookService.updateBook(id, book);
        if (updatedProject != null) {
            Book updatedBook = new Book();
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}