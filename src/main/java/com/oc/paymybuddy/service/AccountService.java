package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.AccountRepository;
import com.oc.paymybuddy.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository AccountRepository) {
        this.accountRepository = AccountRepository;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id) {
        Optional<Account> accountId = accountRepository.findById(id);
        if (accountId.isEmpty()) {
            System.out.println("not exist");
        }
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account accountDetails) {
        log.info("update client");

        Account updateAccount = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("account not found for this id :: " + id));
        updateAccount.setNumberAccount(accountDetails.getNumberAccount());
        updateAccount.setBalance(accountDetails.getBalance());
        return accountRepository.save(updateAccount);
    }

}
