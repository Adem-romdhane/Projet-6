package com.oc.paymybuddy;

import com.oc.paymybuddy.Repositories.UserRepository;
import com.oc.paymybuddy.model.Account;
import com.oc.paymybuddy.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PayMyBuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApplication.class, args);

        User user1= new User(null,"adem","rmdhn","adem@gmail.com","12345",List.of("aa","bbb"));
        Account account1 = new Account();
        account1.setId(null);
        account1.setNumberAccount(12344);
        account1.setUser(user1);

    }

}
