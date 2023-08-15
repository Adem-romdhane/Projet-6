package com.oc.paymybuddy.service;


import com.oc.paymybuddy.Repositories.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService {

    private ITransactionRepository ITransactionRepository;
    @Autowired // Cette annotation est importante pour l'injection de dépendance
    public TransactionService(ITransactionRepository ITransactionRepository) {
        this.ITransactionRepository = ITransactionRepository;
    }

}
