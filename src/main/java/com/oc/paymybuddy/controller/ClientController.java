package com.oc.paymybuddy.controller;

import com.oc.paymybuddy.model.Client;
import com.oc.paymybuddy.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/v1/api/Client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;


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

     /*  @GetMapping("/connexion")
   public String showLoginPage()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/index";
   }
*/


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("client",new Client());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Client client, Model model) {
        //try {
        System.out.println(client);
            UserDetails userDetails = clientService.loadUserByUsername(client.getMail());
            System.out.println("user details:"+userDetails);
            // Vous pouvez stocker les informations de l'utilisateur dans le modèle
            model.addAttribute("userDetails", userDetails);
            return "redirect:clients"; // Redirigez l'utilisateur vers le tableau de bord après la connexion réussie
      /*//  } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Identifiants invalides"); // Gérez les erreurs de connexion
            return "login";
        }*/
    }

    @PostMapping("/process_register")
    public String processRegister(Client client){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientService.saveClient(client);
        return "register_success";
    }

    @GetMapping("/register")
    public String RegistrationForm(Model model ) {
        model.addAttribute("client", new Client());
        return "formClients";
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

    @PostMapping("/save")
    public String save(Model model, Client client) {
        clientService.saveClient(client);
        return "formClients";
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
