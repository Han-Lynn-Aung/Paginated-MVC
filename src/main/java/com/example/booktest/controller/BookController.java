package com.example.booktest.controller;

import com.example.booktest.entity.Book;
import com.example.booktest.repo.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/bookList")
    public String showBooks(Model model) {
        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/newBook")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookRepo.save(book);
        return "redirect:/book/bookList";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/editBook/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-book";
        }
        book.setId(id);
        bookRepo.save(book);
        return "redirect:/book/bookList";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepo.deleteById(id);
        return "redirect:/book/bookList";
    }
}
