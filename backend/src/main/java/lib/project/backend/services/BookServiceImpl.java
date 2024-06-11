package lib.project.backend.services;

import lib.project.backend.model.dto.BookCreationDTO;
import lib.project.backend.model.entity.*;
import lib.project.backend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(BookCreationDTO bookCreationDTO) {
        Book book = new Book(bookCreationDTO);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setName(book.getName());
            existingBook.setDescription(book.getDescription());
            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksByName(String name, String show) {
        // Implement this based on your requirement
        // For example, assuming 'show' is an attribute of Book, you might filter based on both name and show
        return bookRepository.findByNameContainingIgnoreCaseAndShow(name, show);
    }
}