package com.oc.paymybuddy.service;


import com.oc.paymybuddy.Repositories.ClientRepository;
import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }


    public void depositMoney(Transaction transaction, Client client){
        Client client1 = clientRepository.findByMail(transaction.getConnexion());


    }

    public void sendMoney(Transaction transaction, Client currentClient) {
        Client otherClient = clientRepository.findByMail(transaction.getConnexion());

        // debiter le solde du compte emmetteur
        currentClient.getAccount().setBalance(currentClient.getAccount().getBalance() - transaction.getAmount());

        otherClient.getAccount().setBalance(otherClient.getAccount().getBalance() + transaction.getAmount());


        Transaction transactionDeposit = initTransaction(transaction.getAmount(), transaction.getConnexion(), "TRANSFERT");

        currentClient.getAccount().getTransactions().add(transactionDeposit);
        otherClient.getAccount().getTransactions().add(transactionDeposit);

        clientRepository.save(currentClient);
        clientRepository.save(otherClient);

    }

    private Transaction initTransaction(double amount, String otherAccount, String description) {
        Transaction transaction = new Transaction();
        transaction.setId(transaction.getId());
        transaction.setTransactionNumber(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setConnexion(otherAccount);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDate.now());
        return transaction;
    }

}
