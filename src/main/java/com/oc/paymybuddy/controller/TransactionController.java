package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import com.oc.paymybuddy.service.ClientService;
import com.oc.paymybuddy.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    private final ClientService clientService;

    @GetMapping("/transactionPage")
    public String Pagination(Model model,@RequestParam(defaultValue = "0") int page){

        int pageSize = 4;

        // Utilisez la méthode findAll avec pageable pour obtenir une Page
        Page<Transaction> transactionPage = transactionService.getTransactionPage(page,pageSize);
        model.addAttribute("transaction", transactionPage.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",transactionPage.getTotalPages());
        return"clients";
    }

    @PostMapping("/sendMoney")
    public String getSendMoneyForm(Model model, Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientService.getByEmail(authentication.getName());
        transactionService.sendMoney(transaction, currentClient);

        return "redirect:index";
    }


    @PostMapping("/deposit")
    public String deposit(Model model, Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientService.getByEmail(authentication.getName());
        transactionService.depositMoney(transaction, currentClient);
        return "redirect:index";
    }

    @GetMapping("/transactions")
    public String getTransactions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientService.getByEmail(authentication.getName());
        List<Transaction> transactions = currentClient.getAccount().getTransactions();
        model.addAttribute("transactions", transactions);
        return "redirect:index";
    }





    @GetMapping
    public ResponseEntity<List<Transaction>> getAccounts() {
        List<Transaction> transactions = transactionService.findAll();
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
