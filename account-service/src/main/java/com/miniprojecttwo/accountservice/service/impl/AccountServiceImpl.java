package com.miniprojecttwo.accountservice.service.impl;

import com.miniprojecttwo.accountservice.dto.AccountDTO;
import com.miniprojecttwo.accountservice.dto.AccountRegistrationDTO;
import com.miniprojecttwo.accountservice.dto.LoginDTO;
import com.miniprojecttwo.accountservice.entity.Account;
import com.miniprojecttwo.accountservice.entity.PaymentMethod;
import com.miniprojecttwo.accountservice.repo.AccountRepo;
import com.miniprojecttwo.accountservice.repo.PaymentMethodRepo;
import com.miniprojecttwo.accountservice.security.JwtHelper;
import com.miniprojecttwo.accountservice.service.AccountService;
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
    @Autowired
    private PaymentMethodRepo paymentTypeRepository;
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
    public AccountDTO save(AccountRegistrationDTO accountBody) {
        String encodedPassword = bCryptPasswordEncoder.encode(accountBody.getPassword());

        Set<PaymentMethod> paymentTypes = new HashSet<>(Arrays.asList(
                paymentTypeRepository.getPaymentMethodByName("paypal").get(),
                paymentTypeRepository.getPaymentMethodByName("credit card").get(),
                paymentTypeRepository.getPaymentMethodByName("debit card").get()));
        System.out.println("this one");
        System.out.println();
        var dbUser = accountRepository.getUserByUsername(accountBody.getUsername()).orElse(null);
        if(dbUser== null){
            Account account = new Account();
            account.setFirstname(accountBody.getFirstname());
            account.setLastname(accountBody.getLastname());
            account.setEmail(accountBody.getEmail());
            account.setPassword(encodedPassword);
            account.setUsername(accountBody.getUsername());
            System.out.println("before one");
            account.setPaymentMethods(paymentTypes);
            System.out.println("after one");
            var res = paymentTypes.stream().anyMatch(t->t.getName().equals(accountBody.getPreferredPaymentType()));
            if(!res){
                throw new RuntimeException("Payment method not valid");
            }
            account.setPreferredPaymentMethod(accountBody.getPreferredPaymentType());
            account.setShippingAddress(accountBody.getShippingAddress());
            accountRepository.save(account);
            AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

            return accountDTO;
        }else {
            return null;
        }

    }

    @Override
    public ResponseEntity<?> authenticate(LoginDTO credentialsBody) {
        JSONObject responseObject = new JSONObject();
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialsBody.getUsername(),
                            credentialsBody.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("Exception : "+e);
            responseObject.put("credentials","Invalid credentials");
            return ResponseEntity.badRequest().body(responseObject);
        }

        final String accessToken = jwtHelper.generateToken(credentialsBody.getUsername());
        final String refreshToken = jwtHelper.generateRefreshToken(credentialsBody.getUsername());


        responseObject.put("success",true);
        responseObject.put("token","Bearer " +accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.getUserByUsername(username).orElse(null);
    }

    @Override
    public Account update(Long id, Account accountBody) {
        // TO DO
        return null;
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }
}
