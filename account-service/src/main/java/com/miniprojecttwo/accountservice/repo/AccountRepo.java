package com.miniprojecttwo.accountservice.repo;

import com.miniprojecttwo.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    @Query("from Account ")
    List<Account> findAllAccounts();

    Optional<Account> getUserByUsername(String username);
    Optional<Account> getAccountById(Long id);
}
