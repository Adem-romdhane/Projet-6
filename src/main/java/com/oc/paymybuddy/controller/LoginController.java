package com.oc.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getUser()
    {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdmin()
    {
        return "clients";
    }
}
