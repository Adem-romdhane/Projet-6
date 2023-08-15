package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/User")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    @PostMapping("/add")
    public ResponseEntity<Client> addPerson(@RequestBody Client client) {
        log.info("add client");
        return new ResponseEntity<>(ClientService.addClient(client), HttpStatus.CREATED);
    }
}
