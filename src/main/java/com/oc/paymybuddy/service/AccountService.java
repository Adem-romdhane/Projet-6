package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.IAccountRepository;
import com.oc.paymybuddy.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository IAccountRepository) {
        this.accountRepository = IAccountRepository;
    }

    private List<Account> accountList = new ArrayList<>();

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

}
