package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import com.oc.paymybuddy.service.ClientService;
import com.oc.paymybuddy.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final ClientService clientService;
    @PostMapping("/sendMoney")
    public String getSendMoneyForm(Model model, Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientService.getByEmail(authentication.getName());
        transactionService.sendMoney(transaction, currentClient);

        return "redirect:index";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes){


    }

    ,@GetMapping("/transactions")
    public String getTransactions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currentClient = clientService.getByEmail(authentication.getName());
        List<Transaction> transactions = currentClient.getAccount().getTransactions();
        model.addAttribute("transactions",transactions);
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
