package com.oc.paymybuddy.service;


import com.oc.paymybuddy.Repositories.ClientRepository;
import com.oc.paymybuddy.Repositories.TransactionRepository;
import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;

    public Page<Transaction> getTransactionPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return transactionRepository.findAll(pageRequest);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return null;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

//    @Transactional
//    public Transaction saveTransaction(Transaction transaction) {
//        return transactionRepository.save(transaction);
//    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public void deleteTransactionById(Long id) {

    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findAllTransactionsList(Pageable pageable) {
        return transactionRepository.findAll();
    }



    @Transactional
    public void depositMoney(Transaction transaction, Client currentClient) {
        Transaction deposit = initTransaction(transaction.getAmount(), transaction.getConnexion(), "DEPOSIT");
        double balance = currentClient.getAccount().getBalance();
        currentClient.getAccount().setBalance(balance + transaction.getAmount());
        currentClient.getAccount().getTransactions().add(deposit);
        clientRepository.save(currentClient);
    }

    @Transactional
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

    public Page<Transaction> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.transactionRepository.findAll(pageable);
    }

}