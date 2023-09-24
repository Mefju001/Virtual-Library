package com.mefju.virtual_library.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/Login")
    public String LoginPanel()
    {
        return "Logowanie";
    }
    @GetMapping("/odmowa_dostepu")
    public String odmowa()
    {
        return "Odmowa";
    }
}
