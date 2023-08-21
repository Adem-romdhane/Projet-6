package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/Client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;


/*
 @GetMapping("/index")
    public String model(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "4") int size) {
        Page<Client> clients = clientService.findAllClients(PageRequest.of(page, size));
        model.addAttribute("listClients", clients.getContent());
        model.addAttribute("pages",new int[clients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "clients";
    }

*/

    @GetMapping("/index")
    public String model(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "4") int size) {
        Page<Client> clients = clientService.findAllClients(page, size);
        model.addAttribute("listClients", clients.getContent());
        model.addAttribute("pages", new int[clients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "clients";
    }







    @GetMapping("/get")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("clients/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        try {
            Client save = clientService.saveClient(client);
            System.out.println("id client " + save.getId());
            return new ResponseEntity<>("Client ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'ajout du client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
    }

}
