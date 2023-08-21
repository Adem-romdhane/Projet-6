package com.oc.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberAccount;
    private int balance;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;

    public Account(Long id, int numberAccount, int balance, Client client) {
        this.id = id;
        this.numberAccount = numberAccount;
        this.balance = balance;

    }


    public void showBalance() {
        System.out.println("your balance : " + balance);
    }

    public void depositMoney(int amount) {
        balance += amount;
    }

    public void withdrawMoney(int amount) {
        balance -= amount;
    }

    public void transferToAnOtherAccount(int amount, Account User) {
        this.withdrawMoney(amount);
        User.depositMoney(amount);
    }
}
