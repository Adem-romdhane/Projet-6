package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMail(String mail);
}
