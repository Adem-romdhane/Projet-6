package com.oc.paymybuddy.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
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
    private Client client;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(Long id, int numberAccount, int balance, Client client) {
        this.id = id;
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.client = client;
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
