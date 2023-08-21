package com.oc.paymybuddy.Repositories;
import com.oc.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
