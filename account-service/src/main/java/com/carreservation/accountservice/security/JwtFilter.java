package com.carreservation.accountservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;

    private final UserDetailsService userDetailsService;

    @Value("${account-reservation-token}")
    private String account_srv_reservation_token;

    public JwtFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String email = null;
        String token = null;

        final String customHeader = request.getHeader("custom-header");
        List<String> allRoles = new ArrayList<>();
        if(customHeader!= null && customHeader.equals(account_srv_reservation_token)){
            allRoles.add("RESERVATION_SERVICE");
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try{
                email = jwtHelper.getUsernameFromToken(token);
            }catch (Exception e){
                String isRefreshToken = request.getHeader("isRefreshToken");
            }

        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(email);
            boolean isTokenValid = jwtHelper.validateToken(token);
            if (isTokenValid) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // TODO ????
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else{
            if(allRoles.size()>0){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        new AwesomeUserDetails(), null, allRoles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList()));

                // TODO ????
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
