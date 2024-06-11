package lib.project.backend.model.entity;

import jakarta.persistence.*;
import lib.project.backend.model.dto.BookCreationDTO;
import lombok.Data;

@Entity
@Table(name = "\"books\"")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    public Book(BookCreationDTO bookCreationDTO) {
        this.name = bookCreationDTO.getName();
        this.description = bookCreationDTO.getDescription();
    }

    public Book() {

    }
}