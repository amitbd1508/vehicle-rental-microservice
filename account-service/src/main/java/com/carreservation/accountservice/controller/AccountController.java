package com.carreservation.accountservice.controller;



import com.carreservation.accountservice.constants.RestEndpoints;
import com.carreservation.accountservice.dto.AccountDTO;
import com.carreservation.accountservice.dto.AccountRegistrationDTO;
import com.carreservation.accountservice.dto.LoginDTO;
import com.carreservation.accountservice.service.AccountService;
import com.carreservation.accountservice.entity.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEndpoints.ACCOUNTS)
@Api( tags = "Account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(RestEndpoints.REGISTER)
    public ResponseEntity<?> save(@RequestBody AccountRegistrationDTO accountBody){
        AccountDTO account = accountService.save(accountBody);
        if(account== null)
            return ResponseEntity.ok("Duplicate username");

        return ResponseEntity.ok(account);
    }

    // Authenticate a user
    @PostMapping(RestEndpoints.LOGIN)
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO credentialsBody){
        return accountService.authenticate(credentialsBody);
    }

    @GetMapping()
    public ResponseEntity<List<Account>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    // Get a single account by id
//    @GetMapping(RestEndpoints.BY_ID)
//    public ResponseEntity<?> findById(@PathVariable Long id){
//        Optional<Account> account = accountService.findById(id);
//        return ResponseEntity.ok(account);
//    }
//    // Get a single account by id
//    @DeleteMapping(RestEndpoints.BY_ID)
//    public ResponseEntity<?> deleteById(@PathVariable Long id){
//        accountService.deleteById(id);
//        return ResponseEntity.ok("Account deleted successfully !");
//    }

    // Get a single account by id
    @GetMapping("{id}/preferredPaymentMethod")
    public ResponseEntity<?> findPreferredPaymentMethodById(@PathVariable Long id){
        Account account = accountService.findById(id).get();
        return ResponseEntity.ok(account.getPreferredPaymentMethod());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getByUserName(@PathVariable String username){
        var accounts = accountService.findByUsername(username);
        return ResponseEntity.ok(accounts);

    }

}
