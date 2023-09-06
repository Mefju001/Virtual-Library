package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final BookRepository bookRepository;
    public UserController(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/Show")
    public String ListBook(Model themodel)
    {
        List<Book> books = bookRepository.findAll();
        themodel.addAttribute("Book",books);
        return "main";
    }
    @GetMapping("/Menu")
    public String show(Model themodel)
    {
        List<Book> books = bookRepository.findAll();
        themodel.addAttribute("Book",books);
        return "main";
    }
}
