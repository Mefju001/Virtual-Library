package com.mefju.virtual_library.Controller;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Service.BibliotekiService;
import com.mefju.virtual_library.Service.BookService;
import com.mefju.virtual_library.Service.UserService;
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

import java.security.Principal;
import java.util.List;
import java.util.Objects;
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
        if(books.isEmpty())
        {
            books = bookService.FindBookByWydawca(name);
            if(books.isEmpty())
            {
                books = bookService.FindBookByAutor(name);
                themodel.addAttribute("Book", books);
                prepareModel(themodel);
                if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
                }
                return "main";
            }
            themodel.addAttribute("Book", books);
            prepareModel(themodel);
            if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
            }
            return "main";
        }
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }
    @PostMapping("/SzukaniePoCenie")
    public String SzukaniePoCenie(@RequestParam("min") int min, @RequestParam("max") int max, Model themodel, Authentication authentication) {
        if(min <= max && min > 0) {
            List<Book> books = bookService.FindBookByPrice(min, max);
            themodel.addAttribute("Book", books);
            prepareModel(themodel);
            if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
            }
            return "main";
        }
        else{
            themodel.addAttribute("message","Błedne dane");
            if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
            }
            return "main";
        }
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
    @GetMapping("/SortNameASC")
    public String SortNameASC(Model themodel, Authentication authentication) {
        List<Book> books = bookService.SortNameASC();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }
    @GetMapping("/SortNameDESC")
    public String SortNameDESC(Model themodel, Authentication authentication) {
        List<Book> books = bookService.SortNameDESC();
        themodel.addAttribute("Book", books);
        prepareModel(themodel);
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "mainAdmin"; // Zwraca menuadmin, jeśli użytkownik ma rolę admina
        }
        return "main";
    }
    @GetMapping("/Zapowiedzi")
    public String Zapowiedzi(Model themodel, Authentication authentication) {
        List<Book> books = bookService.Zapowiedzi();
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
                                 @RequestParam("newPassword2") String newPassword2,
                                 Principal principal,
                                 Authentication authentication) {

        String username = principal.getName();
        if(Objects.equals(newPassword, newPassword2)) {
            userService.changeUserPassword(username, oldPassword, newPassword);
            if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/MenuAdmin";

            } else {
                return "redirect:/Menu";
            }
        }
        else {
            return "ChangePassword";
        }
    }
        @GetMapping("/DeleteUser")
        public String DeleteUser (Principal principal)
        {
            String username = principal.getName();
            userService.deleteUser(username);
            return "redirect:/Login";
        }
        @GetMapping("/Listauzytkownikow")
        public String Listauzytkownikow (Model themodel)
        {
            List<User> users = userService.ListUsers();
            themodel.addAttribute("users", users);
            return "Uzytkownicy";
        }
    @GetMapping("/ShowFormForMagazyn")
    public String ShowFormForAddforbook() {
        return "dodajksiazkemagazyn";
    }
    @PostMapping("/dodajDoMagazynu")
    public String dodajDoMagazynu(@RequestParam int ksiazka, @RequestParam String biblioteka,Model theModel) {
        Optional<Book> bookOptional = bookService.FindByID(ksiazka);
        Optional<Biblioteki> bibliotekiOptional = bibliotekiService.FindByID(biblioteka);
        if (bookOptional.isPresent() && bibliotekiOptional.isPresent()) {
            Book book = bookOptional.get();
            Biblioteki biblioteki = bibliotekiOptional.get();
            if (bibliotekiService.FindById(book.getId(), biblioteki.getId()).isEmpty()) {
                bibliotekiService.dodajDoMagazynu(book, biblioteki);
                return "redirect:/MenuAdmin";
            } else {
                theModel.addAttribute("message","Ksiazka juz jest dodana w tej bibliotece");
                return "dodajksiazkemagazyn";
            }

        } else {
            theModel.addAttribute("message","złe id ksiazki badz biblioteki");
            return "dodajksiazkemagazyn";
        }
    }
    @GetMapping("/LibraryAdmin")
    public String showLibrary(Model themodel)
    {
        List<Biblioteki>bibliotekis=bibliotekiService.FindAll();
        themodel.addAttribute("Biblioteki",bibliotekis);
        return "BibliotekiAdmin";
    }
    @GetMapping("/ShowFormForBiblioteki")
    public String ShowFormForBiblioteki(Model themodel) {
        Biblioteki biblioteki =new Biblioteki();
        themodel.addAttribute("Biblioteka",biblioteki);
        return "dodajbiblioteke";
    }
    @PostMapping("/savebiblioteka")
    public String dodajbiblioteke(@ModelAttribute("Biblioteka") Biblioteki biblioteki) {

        bibliotekiService.save(biblioteki);
        return "redirect:/LibraryAdmin";
    }
    @GetMapping("/ShowFormForUpdateBiblioteki")
    public String updatebiblioteki(@RequestParam("id") String id, Model theModel) {
        Optional<Biblioteki> biblioteki = bibliotekiService.FindByID(id);
        theModel.addAttribute("Biblioteka", biblioteki);
        return "dodajbiblioteke";
    }

    @GetMapping("/Deletebiblioteka")
    public String Delete(@RequestParam("id") String id) {
        bibliotekiService.delete(id);
        return "redirect:/LibraryAdmin";
    }
}

