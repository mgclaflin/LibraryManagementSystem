package hft.matthew.LibraryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId; // Primary key

    @Column(unique = true, nullable = false)
    private String isbn; // Unique identifier for the book

    @Column(nullable = false)
    private String title; // Title of the book

    @Column(nullable = false)
    private String author; // Author of the book

    @Column(nullable = true)
    private String description; // Optional description of the book

    @Column(nullable = true)
    private int pageCount; // Optional page count of the book

    // Default constructor (required by JPA)
    public Book() {
    }

    // Parameterized constructor
    public Book(String isbn, String title, String author, String description, int pageCount) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.pageCount = pageCount;
    }

    // Getters and setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}