package com.oc.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

    @RolesAllowed("USER")
    @RequestMapping("/*")
    public String getUser()
    {
        return "redirect:/v1/api/Client/index";
    }

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/admin")
    public String getAdmin()
    {
        return "clients";
    }
}
