package com.oc.paymybuddy.service;


import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

  /*  public void createTransaction(Transaction transaction) {
        Transaction transaction1 = new Transaction();
//        transaction1.setOperationDate(transaction1.getOperationDate());
        transaction1.setOperationDescription(transaction1.getOperationDescription());
    }*/

}
