package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/index")
    public String model() {
        return "clients";
    }

    @GetMapping({"/", "/login"})
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping("/register")
    public String processRegister(Client client) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientService.saveClient(client);
        return "redirect:login?register_success";
    }

    @GetMapping("/register")
    public String RegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "formClients";
    }

}
