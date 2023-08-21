package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.ClientRepository;
import com.oc.paymybuddy.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;


    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    //pagination
    public Page<Client> findAllClients(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientRepository.findAll(pageRequest);
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

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public void deleteClientById(Long id) {
        Optional<Client> clientId = clientRepository.findById(id);
        if (clientId.isEmpty()) {
            System.out.println("not exist");
        }
        clientRepository.deleteById(id);
    }


}
