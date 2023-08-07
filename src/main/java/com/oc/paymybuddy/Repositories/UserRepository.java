package com.oc.paymybuddy.Repositories;

import com.oc.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
