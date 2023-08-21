package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
