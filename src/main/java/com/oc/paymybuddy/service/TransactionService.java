package com.oc.paymybuddy.service;


import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void deleteTransactionById(Long id) {
        Optional<Transaction>transactionId = transactionRepository.findById(id);
        if (transactionId.isEmpty()){
            System.out.println("not exist");
        }
        transactionRepository.deleteById(id);
    }

}
