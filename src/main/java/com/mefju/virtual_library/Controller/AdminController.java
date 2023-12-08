package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.*;
import com.mefju.virtual_library.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final BookService bookService;
    private final BibliotekiService bibliotekiService;
    private final UserService userService;

    @Autowired
    public AdminController(BookService bookService, BibliotekiService bibliotekiService, UserService userService) {
        this.bibliotekiService = bibliotekiService;
        this.bookService = bookService;
        this.userService = userService;
    }

    private void prepareModel(Model themodel) {
        List<String> categories = bookService.TypeAll();
        themodel.addAttribute("categories", categories);
        List<Biblioteki> biblioteki = bibliotekiService.FindAll();
        themodel.addAttribute("lokal", biblioteki);
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = principal.getName();
        themodel.addAttribute("username", loggedInUsername);
    }

    @GetMapping("/MenuAdmin")
    public String show(Model themodel) {
        List<Book> books = bookService.FindAll();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        return "mainAdmin";
    }

    @GetMapping("/ShowFormForAdd")
    public String ShowFormForAdd(Model themodel) {
        Book book = new Book();
        themodel.addAttribute("Book", book);
        return "Formularz";
    }

    @PostMapping("/save")
    public String addKarnet(@ModelAttribute("Book") Book book, MultipartFile file) {

        String picturePath = "\\Img\\" + file.getOriginalFilename();
        book.setPicturePath(picturePath);
        bookService.save(book);
        return "redirect:/MenuAdmin";
    }

    @GetMapping("/ShowFormForUpdate")
    public String addKarnet(@RequestParam("id") int id, Model theModel) {
        Optional<Book> book = bookService.FindByID(id);
        theModel.addAttribute("Book", book);
        return "Formularz";
    }

    @GetMapping("/Delete")
    public String Delete(@RequestParam("id") int id) {
        bookService.DeleteById(id);
        return "redirect:/MenuAdmin";
    }

    @PostMapping("/szukanie")
    public String szukanie(@RequestParam("name") String name, Model themodel, Authentication authentication) {
        List<Book> books = bookService.FindBookByName(name);
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @PostMapping("/SzukaniePoCenie")
    public String SzukaniePoCenie(@RequestParam("min") int min, @RequestParam("max") int max, Model themodel, Authentication authentication) {
        List<Book> books = bookService.FindBookByPrice(min, max);
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @PostMapping("/szukanierodzajów")
    public String szukanietype(@RequestParam("type") String type, Model themodel, Authentication authentication) {
        List<Book> books = bookService.FindBookByType(type);
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @PostMapping("/szukaniebiblioteki")
    public String szukaniebiblioteki(@RequestParam("biblioteka") String biblioteka, Model themodel, Authentication authentication) {
        List<Book> books = bookService.FindBookByLibrary(biblioteka);
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @GetMapping("/Sort")
    public String Sortowanie(Model themodel, Authentication authentication) {
        List<Book> books = bookService.SortPriceASC();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @GetMapping("/Sortmal")
    public String Sortowaniemalejaco(Model themodel, Authentication authentication) {
        List<Book> books = bookService.SortPriceDSC();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @GetMapping("/popular")
    public String popular(Model themodel, Authentication authentication) {
        List<Book> books = bookService.SortByPopular();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }

    @GetMapping("/changepasswordform")
    public String showchangepasswordform() {
        return "ChangePassword";
    }

    @PostMapping("/changepassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes, Authentication authentication) {

        String username = principal.getName();
        String changePasswordResult = userService.changeUserPassword(username, oldPassword, newPassword);
        if (changePasswordResult.equals("success")) {
            redirectAttributes.addFlashAttribute("successMessage", "Hasło zostało zmienione pomyślnie");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", changePasswordResult);
        }
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/MenuAdmin";

        }
        else {
            return "redirect:/Menu";
        }
    }
        @GetMapping("/DeleteUser")
        public String DeleteUser (Principal principal)
        {
            String username = principal.getName();
            userService.deleteUser(username);
            return "redirect:/Login";
        }
}

