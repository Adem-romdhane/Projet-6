package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.AccountRepository;
import com.oc.paymybuddy.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
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
