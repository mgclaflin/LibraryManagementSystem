package hft.matthew.LibraryManagementSystem.controller;

import hft.matthew.LibraryManagementSystem.model.Book;
import hft.matthew.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private final BookService bookService;

    @Autowired
    public BookWebController(BookService bookService){
        this.bookService = bookService;
    }

    // Show list of all books in the web UI
    @GetMapping
    public String viewAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";  // Renders the "books.html" template with the list of books
    }

    // Show a specific book based on ISBN
    @GetMapping("/{isbn}")
    public String viewBook(@PathVariable String isbn, Model model) {
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "book-details"; // Renders the "book-details.html" template with the book details
    }

    // Add a new book (show form)
    @GetMapping("/new")
    public String addNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form"; // Renders the form for adding a new book
    }

    // Handle form submission to add a new book
    @PostMapping("/new")
    public String saveNewBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books"; // Redirects to the book list after saving
    }
}
