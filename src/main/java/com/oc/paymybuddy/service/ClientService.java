package com.oc.paymybuddy.service;

import com.oc.paymybuddy.Repositories.IClientRepository;
import com.oc.paymybuddy.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {
    private static IClientRepository IClientRepository;

    @Autowired // Cette annotation est importante pour l'injection de dépendance
    public ClientService(IClientRepository IClientRepository) {
        this.IClientRepository = IClientRepository;
    }


    public static Client addClient(Client client) {
        return IClientRepository.save(client);
    }

}
