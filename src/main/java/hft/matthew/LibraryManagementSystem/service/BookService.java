package hft.matthew.LibraryManagementSystem.service;

import hft.matthew.LibraryManagementSystem.model.Book;
import hft.matthew.LibraryManagementSystem.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    //Save or update existing Book
    public Book saveBook(Book book){
        return bookRepo.save(book);
    }

    //Find all books
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    // Find a book by its ISBN
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    // Find a book by its ID
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    //Find book by its author
    public List<Book> getBookByAuthor(String author){
        return bookRepo.findByAuthor(author);
    }

    //Delete a book by its ID
    //need to build in functionality that this also deletes all copies of the book from the records
    public void deleteBook(Long id){
        bookRepo.deleteById(id);
    }



}
