package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import com.oc.paymybuddy.service.ClientServiceImpl;
import com.oc.paymybuddy.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;
    private final TransactionServiceImpl transactionService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "pageNo" , defaultValue = "1") int pageNo) {
        int pageSize = 5;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientServiceImpl.getByEmail(authentication.getName());
        Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize);
        List<Transaction> listTransactions = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listTransactions", listTransactions);
        System.out.println("TRANSACTIONS : "+ client.getAccount().getTransactions());
        model.addAttribute("friends", client.getFriendsList());
        model.addAttribute("transactions", client.getAccount().getTransactions());
        System.out.println("client: " + client);
        return "clients";
    }
    @GetMapping({ "/login"})
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }


    @PostMapping("/addConnection")
    public String addConnection(Model model, String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientServiceImpl.getByEmail(authentication.getName());
        Client addConnection = clientServiceImpl.addConnection(client.getMail(), email);
        model.addAttribute("client", addConnection);
        model.addAttribute("friends", client.getFriendsList());


        return "redirect:index?pageNo=1";
    }


    @PostMapping("/register")
    public String processRegister(Client client) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientServiceImpl.saveClient(client);
        return "redirect:login?register_success";
    }

    @GetMapping("/register")
    public String RegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "formClients";
    }

}
