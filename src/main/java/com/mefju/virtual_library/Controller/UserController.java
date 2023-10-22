package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Service.BibliotekiService;
import com.mefju.virtual_library.Service.BookService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final BookService bookService;
    private final BibliotekiService bibliotekiService;

    public void prepareModel(Model themodel)
    {
        List<String>categories =bookService.TypeAll();
        themodel.addAttribute("categories",categories);
        List<Biblioteki> biblioteki =bibliotekiService.FindAll();
        themodel.addAttribute("lokal",biblioteki);
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = principal.getName();
        themodel.addAttribute("username",loggedInUsername);
    }
    public UserController(BookService bookService, BibliotekiService bibliotekiService)
    {
        this.bibliotekiService = bibliotekiService;
        this.bookService = bookService;
    }

    @GetMapping("/Menu")
    public String show(Model themodel)
    {
        List<Book> books = bookService.FindAll();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @PostMapping("/szukanie")
    public String szukanie(@RequestParam("name")String name, Model themodel)
    {
        List<Book> books=bookService.FindBookByName(name);
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @PostMapping("/SzukaniePoCenie")
    public String SzukaniePoCenie(@RequestParam("min")int min, @RequestParam("max")int max, Model themodel)
    {
        List<Book> books=bookService.FindBookByPrice(min, max);
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @PostMapping("/szukanierodzajów")
    public String szukanietype(@RequestParam("type")String type, Model themodel)
    {
        List<Book> books=bookService.FindBookByType(type);
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @PostMapping("/szukaniebiblioteki")
    public String szukaniebiblioteki(@RequestParam("biblioteka")String biblioteka, Model themodel)
    {
        List<Book> books=bookService.FindBookByLibrary(biblioteka);
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        return "main";
    }
    @GetMapping("/Sort")
    public String Sortowanie(Model themodel)
    {
        List<Book> books = bookService.SortPriceASC();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @GetMapping("/Sortmal")
    public String Sortowaniemalejaco(Model themodel)
    {
        List<Book> books = bookService.SortPriceDSC();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
    @GetMapping("/popular")
    public String popular(Model themodel)
    {
        List<Book> books = bookService.SortByPopular();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "main";
    }
}
