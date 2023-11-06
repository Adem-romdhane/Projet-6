package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import com.oc.paymybuddy.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.getByEmail(authentication.getName());
        System.out.println("TRANSACTIONS : "+ client.getAccount().getTransactions());
        model.addAttribute("friends", client.getFriendsList());
        model.addAttribute("transactions", client.getAccount().getTransactions());
        System.out.println("client: " + client);
        return "clients";
    }

    @GetMapping({"/", "/login"})
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }


    @PostMapping("/addConnection")
    public String addConnection(Model model, String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.getByEmail(authentication.getName());
        Client addConnection = clientService.addConnection(client.getMail(), email);
        model.addAttribute("client", addConnection);
        model.addAttribute("friends", client.getFriendsList());

        return "redirect:index";
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
