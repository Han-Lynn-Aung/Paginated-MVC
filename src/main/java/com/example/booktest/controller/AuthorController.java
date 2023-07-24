package com.example.booktest.controller;

import com.example.booktest.entity.Author;
import com.example.booktest.entity.Book;
import com.example.booktest.repo.AuthorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepo authorRepo;

    public AuthorController(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping("/authors/authorList")
    public String showAuthors(Model model) {
        List<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "author-list";
    }

    @GetMapping("/authors/newAuthor")
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/authors/saveAuthor")
    public String saveAuthor(@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Result : " + result);
            redirectAttributes.addFlashAttribute("saveError", true);
        } else {
            System.out.println("Saving author: " + author.toString());
            authorRepo.save(author);
            System.out.println("Author saved: " + author.toString());
            redirectAttributes.addFlashAttribute("saveSuccess", true);
        }
        return "redirect:/authors/authorList";
    }

    @GetMapping("/authors/editAuthor/{id}")
    public String showEditAuthorForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        model.addAttribute("author", author);
        return "edit-author";
    }

    @PostMapping("/authors/editAuthor/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-author";
        }
        Author existingAuthor = authorRepo.getById(id);
        if (existingAuthor == null){
            return "redirect:/authors/authorList";
        }
        existingAuthor.setName(author.getName());
        existingAuthor.setDateOfBirth(author.getDateOfBirth());
        existingAuthor.setAddress(author.getAddress());

        authorRepo.save(existingAuthor);
        return "redirect:/authors/authorList";
    }

    @GetMapping("/authors/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepo.deleteById(id);
        return "redirect:/authors/authorList";
    }
}
