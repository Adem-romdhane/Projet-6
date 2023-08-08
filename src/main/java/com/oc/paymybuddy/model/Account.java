package com.oc.paymybuddy.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    private Long Id;
    private int numberAccount;
    private int balance;
    private User user;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(Long id, int numberAccount, int balance, User user) {
        Id = id;
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.user = user;
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
