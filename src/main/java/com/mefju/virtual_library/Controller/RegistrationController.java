package com.mefju.virtual_library.Controller;


import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.Role;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model themodel) {
        User user = new User();
        themodel.addAttribute("users",user);
        return "registration-form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("users") User users, Model theModel) {
            if (userService.Exist(users)) {
                theModel.addAttribute("message","ten użytkownik już istnieje");
                return "registration-form";
            }
            else {
                users.setEnabled(true);
                users.setPassword("{noop}"+users.getPassword());
                System.out.println(users.getUsername());
                System.out.println(users.getPassword());
                userService.AddUser(users);
                Role authority = new Role();
                authority.setID(users.getUsername());
                authority.setRola("ROLE_USER");
                userService.AddRole(authority);
                return "redirect:/login"; // przekierowanie po rejestracji
            }
    }
    /*@GetMapping("/Edycja")
    public String edycjakonta(Model themodel)
    {
        Optional<User> user = userService.FindByID(id);
        theModel.addAttribute("Book",book);
    }
    @GetMapping("/Usun")
    public String usunkonto(Model themodel)
    {
        userService.
        return "Logowanie";
    }*/
}

