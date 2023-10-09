package com.mefju.virtual_library.Controller;


import com.mefju.virtual_library.Entity.Role;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Repository.RoleRepository;
import com.mefju.virtual_library.Repository.UserRepository;
import com.mefju.virtual_library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegistrationController(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService =  userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model themodel) {
        User user = new User();
        themodel.addAttribute("users",user);
        return "registration-form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("users") User users, Model theModel) {
            if (userRepository.existsById(users.getUsername())) {
                theModel.addAttribute("message","ten użytkownik już istnieje");

                return "registration-form";
            }
            else {
                users.setEnabled(true);
                users.setPassword("{noop}"+users.getPassword());
                System.out.println(users.getUsername());
                System.out.println(users.getPassword());
                userService.SaveUSR(users);
                Role authority = new Role();
                authority.setID(users.getUsername());
                authority.setRola("ROLE_USER");
                roleRepository.save(authority);
                return "redirect:/login"; // przekierowanie po rejestracji
            }
    }
}

