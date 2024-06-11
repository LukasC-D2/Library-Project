package lib.project.backend.services;

import lib.project.backend.model.dto.BookCreationDTO;
import lib.project.backend.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book createBook(BookCreationDTO bookCreationDTO);
    Book getBookById(Long id);
    void deleteBookById(Long id);
    Book updateBook(Long id, Book book);
    List<Book> getAllBooks();
    List<Book> findBooksByName(String name, String show);

}