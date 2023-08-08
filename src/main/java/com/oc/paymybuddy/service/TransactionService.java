package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService {
    private TransactionRepository transactionRepository;
}
