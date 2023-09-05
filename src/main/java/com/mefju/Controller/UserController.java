package com.mefju.Controller;

import com.mefju.Entity.Book_entity;
import com.mefju.Repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private BookRepository bookRepository;
    public UserController(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/Show")
    public String ListBook(Model themodel)
    {
        List<Book_entity> books = bookRepository.findAll();
        themodel.addAttribute("Book",books);
        return ""; //TODO nazwa html
    }
}
