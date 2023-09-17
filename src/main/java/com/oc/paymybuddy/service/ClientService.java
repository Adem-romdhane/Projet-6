package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.ClientRepository;
import com.oc.paymybuddy.model.Account;
import com.oc.paymybuddy.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;
    public Client saveClient(Client client) {
        Account account = new Account();
        account.setBalance(0.0);
        client.setAccount(account);
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }


    public Client updateClient(Long id, Client clientDetails) {
        log.info("update client");

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("client not found for this id :: " + id));
        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setMail(clientDetails.getMail());
        client.setPassword(clientDetails.getPassword());
        client.setAccount(clientDetails.getAccount());
        return clientRepository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        System.out.println("mail" + mail);
        Client clientByEmail = clientRepository.findByMail(mail);
        System.out.println("user by email " + clientByEmail);
        if (clientByEmail != null) {
            System.out.println("user by email " + clientByEmail);
            List<GrantedAuthority> authority = new ArrayList<>();
            authority.add(new SimpleGrantedAuthority("admin"));
            return new User(clientByEmail.getMail(), clientByEmail.getPassword(), authority);
        }
        throw new UsernameNotFoundException("Invalid username or password.");
    }
}
