package com.example.booktest.controller;

import com.example.booktest.entity.Author;
import com.example.booktest.entity.Book;
import com.example.booktest.repo.AuthorRepo;
import com.example.booktest.repo.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookRepo bookRepo;

    private final AuthorRepo authorRepo;

    public BookController(BookRepo bookRepo,AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books/bookList")
    public String showAuthors(Model model) {
        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/books/newBook")
    public String showAuthorForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/books/saveBook")
    public String saveBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookRepo.save(book);
        return "redirect:/books/bookList";
    }

    @GetMapping("/books/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Book ID: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/books/editBooks/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-book";
        }
        Book existingBook = bookRepo.getById(id);
        Author exitingAuthor = authorRepo.getById(id);
        if (existingBook == null){
            return "redirect:/books/bookList";
        }
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        existingBook.setPublishedDate(book.getPublishedDate());
        existingBook.setAuthor(exitingAuthor);

        bookRepo.save(existingBook);
        return "redirect:/books/bookList";
    }

    @GetMapping("/books/deleteBooks/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepo.deleteById(id);
        return "redirect:/books/bookList";
    }
}
