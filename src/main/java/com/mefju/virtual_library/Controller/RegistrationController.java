package com.mefju.virtual_library.Controller;


import com.mefju.virtual_library.Entity.Role;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final UserService userService;
    @Autowired
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
    public String registerUser(@ModelAttribute("users") User users, Model theModel, @RequestParam String password2) {
            if (userService.Exist(users)) {
                theModel.addAttribute("message","ten użytkownik już istnieje");
                return "registration-form";
            }
            else if(users.getPassword().equals(password2))
            {
                users.setEnabled(true);
                users.setPassword("{noop}"+users.getPassword());
                userService.AddUser(users);
                Role authority = new Role();
                authority.setID(users.getUsername());
                authority.setRola("ROLE_USER");
                userService.AddRole(authority);
                return "redirect:/Login"; // przekierowanie po rejestracji
            }
            else {
                theModel.addAttribute("message","Złe hasło");
                return "registration-form";
            }
    }
}

