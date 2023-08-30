package com.oc.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberAccount;
    private double balance;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;

    public Account(Long id, double balance) {
        this.id = id;
        this.numberAccount = UUID.randomUUID().toString();
        this.balance = balance;
    }
}
