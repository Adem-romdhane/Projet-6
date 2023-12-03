package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepositoryImpl extends PagingAndSortingRepository<Transaction,Long> {
}
