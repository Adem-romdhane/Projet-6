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
import java.util.Collections;
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

    public Client getByEmail(String mail) {
        return clientRepository.findByMail(mail);
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

    public Client addConnection(String email, String otherEmail) {
        Client clientByEmail = clientRepository.findByMail(email);
        if (clientByEmail == null) throw new RuntimeException("client not found");

        Client clientByOtherEmail = clientRepository.findByMail(otherEmail);
        if (clientByOtherEmail == null) throw new RuntimeException("client not found");
        clientByOtherEmail.getFriendsList().add(clientByEmail);
        clientByOtherEmail = clientRepository.save(clientByEmail);
        clientByEmail.getFriendsList().add(clientByOtherEmail);
        return clientRepository.save(clientByOtherEmail);
    }

    public List<Client> getFriendsList(Long clientId) {
        // Obtenez le client par son ID
        Client client = clientRepository.findById(clientId).orElse(null);

        // Vérifiez si le client existe
        if (client != null) {
            // Renvoie la liste d'amis du client
            return client.getFriendsList();
        } else {
            // Si le client n'est pas trouvé, vous pouvez choisir de lever une exception ou renvoyer une liste vide.
            // Dans cet exemple, je renvoie une liste vide.
            return Collections.emptyList();
        }
    }
}
