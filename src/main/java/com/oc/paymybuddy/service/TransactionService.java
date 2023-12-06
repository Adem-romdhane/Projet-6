package com.oc.paymybuddy.service;

import com.oc.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransactions();
    void saveTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    void deleteTransactionById(Long id);
    Page<Transaction> findPaginated(int pageNo, int pageSize);
}
