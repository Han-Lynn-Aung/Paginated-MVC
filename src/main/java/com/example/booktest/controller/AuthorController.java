package com.example.booktest.controller;

import com.example.booktest.entity.Author;
import com.example.booktest.repo.AuthorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepo authorRepo;

    public AuthorController(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping("/authorList")
    public String showAuthors(Model model) {
        List<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "author-list";
    }

    @GetMapping("/newAuthor")
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author-form";
        }
        authorRepo.save(author);
        return "redirect:/author/authorList";
    }

    @GetMapping("/editAuthor/{id}")
    public String showEditAuthorForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        model.addAttribute("author", author);
        return "edit-author";
    }

    @PostMapping("/editAuthor/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-author";
        }
        author.setId(id);
        authorRepo.save(author);
        return "redirect:/author/authorList";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepo.deleteById(id);
        return "redirect:/author/authorList";
    }
}
