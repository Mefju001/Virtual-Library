package com.mefju.virtual_library.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/Login")
    public String LoginPanel()
    {
        return "Logowanie";
    }
    @RequestMapping("/redirect")
    public String redirect() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/MenuAdmin";
        } else if (role.equals("ROLE_USER")) {
            return "redirect:/Menu";
        } else {
                return "redirect:/odmowa_dostepu";
        }
    }
    @GetMapping("/odmowa_dostepu")
    public String odmowa()
    {
        return "Odmowa";
    }
}
