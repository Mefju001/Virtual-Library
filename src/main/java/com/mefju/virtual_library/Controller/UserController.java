package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Service.BibliotekiService;
import com.mefju.virtual_library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final BookService bookService;
    private final BibliotekiService bibliotekiService;
    @Autowired
    public UserController(BookService bookService, BibliotekiService bibliotekiService)
    {
        this.bibliotekiService = bibliotekiService;
        this.bookService = bookService;
    }
    private void prepareModel(Model themodel)
    {
        List<String>categories =bookService.TypeAll();
        themodel.addAttribute("categories",categories);
        List<Biblioteki> biblioteki =bibliotekiService.FindAll();
        themodel.addAttribute("lokal",biblioteki);
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = principal.getName();
        themodel.addAttribute("username",loggedInUsername);
    }


    @GetMapping("/Menu")
    public String show(Model themodel)
    {
        List<Book> books = bookService.FindAll();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }

}
