package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    Optional<Client> findById(Long id);

    @Query("""
                select client from Client client where client.mail=?1
            """)
     Client findClientByMail(String mail);

 // Page<Client> findByNameContains(String kw, Pageable pageable);
}
