package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import com.oc.paymybuddy.service.ClientServiceImpl;
import com.oc.paymybuddy.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    private final TransactionServiceImpl transactionServiceImpl;
    private final TransactionRepository transactionRepository;

    private final ClientServiceImpl clientServiceImpl;




    @PostMapping("/sendMoney")
    public String getSendMoneyForm(Model model, Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientServiceImpl.getByEmail(authentication.getName());
        transactionServiceImpl.sendMoney(transaction, currentClient);

        return "redirect:index";
    }


    @PostMapping("/deposit")
    public String deposit(Model model, Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientServiceImpl.getByEmail(authentication.getName());
        transactionServiceImpl.depositMoney(transaction, currentClient);
        return "redirect:index";
    }

    @GetMapping("/transactions")
    public String getTransactions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientServiceImpl.getByEmail(authentication.getName());
        List<Transaction> transactions = currentClient.getAccount().getTransactions();
        model.addAttribute("transactions", transactions);
        return "redirect:index";
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> getAccounts() {
        List<Transaction> transactions = transactionServiceImpl.findAll();
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
