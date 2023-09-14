package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final BookRepository bookRepository;

    public AdminController(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/Menu")
    public String show(Model themodel)
    {
        List<Book> books = bookRepository.findAll();
        themodel.addAttribute("Book",books);

        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
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
        bookRepository.save(book);
        return "redirect:/Menu";
    }
    @GetMapping("/ShowFormForUpdate")
    public String addKarnet(@RequestParam("id") int id, Model theModel)
    {
        Optional<Book> book = bookRepository.findById(id);
        theModel.addAttribute("Book",book);
        return "Formularz";
    }
    @GetMapping("/Delete")
    public String Delete(@RequestParam("id") int id)
    {
        bookRepository.deleteById(id);
        return "redirect:/Menu";
    }
    @PostMapping("/szukanie")
    public String szukanie(@RequestParam("name")String name, Model themodel) {
        List<Book> books=bookRepository.findBooksByNameLike(name);
        themodel.addAttribute("Book",books);
        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
    }
    @PostMapping("/szukanierodzajów")
    public String szukanietype(@RequestParam("type")String type, Model themodel) {
        List<Book> books=bookRepository.findBooksByTypeLike(type);
        themodel.addAttribute("Book",books);
        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
    }
    @GetMapping("/Sort")
    public String Sortowanie(Model themodel)
    {
        List<Book> books = bookRepository.SortPrice();
        themodel.addAttribute("Book",books);
        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
    }
    @GetMapping("/Sortmal")
    public String Sortowaniemalejaco(Model themodel)
    {
        List<Book> books = bookRepository.SortPricemalejaco();
        themodel.addAttribute("Book",books);
        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
    }
    @GetMapping("/popular")
    public String popular(Model themodel)
    {
        List<Book> books = bookRepository.popular();
        themodel.addAttribute("Book",books);
        List<String>categories =bookRepository.Typeall();
        themodel.addAttribute("categories",categories);
        return "main";
    }
}
