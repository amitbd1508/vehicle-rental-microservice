package com.carreservation.accountservice.service;



import com.carreservation.accountservice.dto.AccountDTO;
import com.carreservation.accountservice.dto.AccountRegistrationDTO;
import com.carreservation.accountservice.dto.LoginDTO;
import com.carreservation.accountservice.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    ResponseEntity<?> save(AccountRegistrationDTO accountBody);

    ResponseEntity<?> authenticate(LoginDTO credentialsBody);

    List<Account> findAllAccounts();

    Optional<Account> findById(String id);

    void deleteById(String id);

    Account findByUsername(String username);

    Account update(Long id, Account accountBody);
}
