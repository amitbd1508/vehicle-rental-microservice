package com.carreservation.accountservice.service.impl;

import com.carreservation.accountservice.dto.AccountDTO;
import com.carreservation.accountservice.dto.AccountRegistrationDTO;
import com.carreservation.accountservice.dto.LoginDTO;
import com.carreservation.accountservice.entity.Roles;
import com.carreservation.accountservice.repo.AccountRepo;
import com.carreservation.accountservice.service.AccountService;
import com.carreservation.accountservice.entity.Account;
import com.carreservation.accountservice.security.JwtHelper;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepository;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiry}")
    private String expiry;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<?> save(AccountRegistrationDTO accountBody) {
        String encodedPassword = bCryptPasswordEncoder.encode(accountBody.getPassword());


        var dbUser = accountRepository.getUserByUsername(accountBody.getUsername()).orElse(null);
        if(dbUser== null){
            Account account = new Account();
            account.setFirstname(accountBody.getFirstname());
            account.setLastname(accountBody.getLastname());
            account.setEmail(accountBody.getEmail());
            account.setPassword(encodedPassword);
            account.setUsername(accountBody.getUsername());
            account.setPaymentInfo(accountBody.getPaymentInfo());
            account.setAddress(accountBody.getAddress());
            account.setRole(Roles.USER);
            accountRepository.save(account);
            AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

            LoginDTO loginDTO  =  new LoginDTO();
            loginDTO.setPassword(accountBody.getPassword());
            loginDTO.setUsername(accountBody.getUsername());
            return authenticate(loginDTO);

        }else {
            return ResponseEntity.status(HttpStatus.OK).body("User already exist");
        }

    }

    @Override
    public ResponseEntity<AccountDTO> authenticate(LoginDTO credentialsBody) {
        JSONObject responseObject = new JSONObject();
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialsBody.getUsername(),
                            credentialsBody.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("Exception : "+e);
            responseObject.put("credentials","Invalid credentials");
            return ResponseEntity.badRequest().body(null);
        }

        final String accessToken = jwtHelper.generateToken(credentialsBody.getUsername());
        final String refreshToken = jwtHelper.generateRefreshToken(credentialsBody.getUsername());



        var account  = findByUsername(credentialsBody.getUsername());
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        accountDTO.setAccessToken("Bearer " +accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);

    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.getUserByUsername(username).orElse(null);
    }

    @Override
    public Account update(Long id, Account accountBody) {

        return null;
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }
}
