package com.oc.paymybuddy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<Client> friendsList;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
