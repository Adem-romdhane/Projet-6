package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Transaction;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
}
