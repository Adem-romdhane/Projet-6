package com.oc.paymybuddy.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private Long userId;
    private int balance;
    private List<String> transactions;

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
