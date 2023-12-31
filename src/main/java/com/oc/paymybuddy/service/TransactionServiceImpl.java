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
import java.util.stream.Collectors;

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
        // Récupération du montant à transférer et du solde actuel du client
        double amountToTransfer = transaction.getAmount();
        double currentBalance = currentClient.getAccount().getBalance();

        // Vérifier si le montant à transférer est valide
        if (amountToTransfer <= 0) {
            throw new IllegalArgumentException("Le montant à transférer doit être supérieur à zéro");
        }

        // Calculer le montant de la commission de monétisation (0,5 %)
        double monetizationFee = amountToTransfer * 0.005; // 0,5%

        // Calculer le montant réel à transférer après déduction de la commission
        double actualTransferAmount = amountToTransfer - monetizationFee;

        // Vérifier si le solde est suffisant pour le transfert incluant la commission
        if (currentBalance >= amountToTransfer) {
            // Recherche du client destinataire
            Client otherClient = clientRepository.findByMail(transaction.getConnexion());

            // Vérifier si le client destinataire existe
            if (otherClient == null) {
                throw new IllegalArgumentException("Le destinataire avec cette adresse e-mail n'existe pas.");
            }

            // Débiter le solde du compte émetteur
            currentClient.getAccount().setBalance(currentBalance - amountToTransfer);

            // Créer une transaction distincte pour le client destinataire
            Transaction transactionReceiver = initTransaction(actualTransferAmount, transaction.getConnexion(), "TRANSFER");

            // Associer la transaction destinataire au client destinataire
            otherClient.getAccount().getTransactions().add(transactionReceiver);

            // Modifier la valeur de transaction.getAmount() pour le montant réellement transféré
            transaction.setAmount(actualTransferAmount);

            // Sauvegarder les modifications pour chaque client séparément
            clientRepository.save(currentClient);
            clientRepository.save(otherClient);
        } else {
            // Gérer le cas où le solde est insuffisant pour le transfert
            throw new IllegalStateException("Solde insuffisant pour effectuer ce transfert");
        }
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