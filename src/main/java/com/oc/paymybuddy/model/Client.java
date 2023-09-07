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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name ="client_id")
    private List<Client> friendsList;

    @OneToOne
    private Account account;

    public Client(String firstName, String lastName, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }
}
