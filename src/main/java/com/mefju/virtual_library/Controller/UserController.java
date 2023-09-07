package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final BookRepository bookRepository;
    public UserController(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/Menu")
    public String show(Model themodel)
    {
        List<Book> books = bookRepository.findAll();
        themodel.addAttribute("Book",books);

        Book book = new Book();
        themodel.addAttribute("Book2",book);
        return "main";
    }
    @GetMapping("/ShowFormForAdd")
    public String ShowFormForAdd(Model theModel)
    {
        Book book = new Book();
        theModel.addAttribute("Book",book);
        return "Formularz";
    }
    @PostMapping("/save")
    public String addKarnet(@ModelAttribute("Book") Book book)
    {
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
}
