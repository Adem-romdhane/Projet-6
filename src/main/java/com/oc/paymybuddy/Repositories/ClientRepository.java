package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    Optional<Client> findById(Long id);


}
