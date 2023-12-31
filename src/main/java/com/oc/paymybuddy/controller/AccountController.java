package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Account;
import com.oc.paymybuddy.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        try {
            Account save = accountService.saveAccount(account);
            return new ResponseEntity<>("Compte ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'ajout du compte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Account accountUpdate(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
}
