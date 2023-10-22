package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.*;
import com.mefju.virtual_library.Service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

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
    public AdminController(BookService bookService, BibliotekiService bibliotekiService)
    {
        this.bibliotekiService = bibliotekiService;
        this.bookService = bookService;
    }

    @GetMapping("/MenuAdmin")
    public String show(Model themodel)
    {
        List<Book> books = bookService.FindAll();
        themodel.addAttribute("Book",books);
        prepareModel(themodel);
        return "mainAdmin";
    }
    @GetMapping("/ShowFormForAdd")
    public String ShowFormForAdd(Model themodel)
    {
        Book book = new Book();
        themodel.addAttribute("Book",book);
        return "Formularz";
    }
    @PostMapping("/save")
    public String addKarnet(@ModelAttribute("Book") Book book,MultipartFile file)
    {

        String picturePath = "\\Img\\" + file.getOriginalFilename();
        book.setPicturePath(picturePath);
        bookService.save(book);
        return "redirect:/Menu";
    }
    @GetMapping("/ShowFormForUpdate")
    public String addKarnet(@RequestParam("id") int id, Model theModel)
    {
        Optional<Book> book = bookService.FindByID(id);
        theModel.addAttribute("Book",book);
        return "Formularz";
    }
    @GetMapping("/Delete")
    public String Delete(@RequestParam("id") int id)
    {
        bookService.DeleteById(id);
        return "redirect:/Menu";
    }
}
