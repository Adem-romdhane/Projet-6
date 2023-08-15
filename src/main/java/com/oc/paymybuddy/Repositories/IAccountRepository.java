package com.oc.paymybuddy.Repositories;
import com.oc.paymybuddy.model.Account;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface IAccountRepository extends JpaRepository<Account,Long> {

    Account save(Account account);

}
