package com.oc.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {


    private Long id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private List<String> friendsList;
    private Account account;

    public User(Long id, String firstName, String lastName, String mail, String password, List<String> friendsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.friendsList = friendsList;
    }
}
