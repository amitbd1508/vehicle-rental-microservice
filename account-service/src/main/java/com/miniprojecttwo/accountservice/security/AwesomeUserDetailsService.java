package com.miniprojecttwo.accountservice.security;

import com.miniprojecttwo.accountservice.repo.AccountRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class AwesomeUserDetailsService implements UserDetailsService {

    private final AccountRepo userRepo;

    public AwesomeUserDetailsService(AccountRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.getUserByUsername(username);
        var userDetails = new AwesomeUserDetails(user.orElse(null));
        return userDetails;
    }

}
