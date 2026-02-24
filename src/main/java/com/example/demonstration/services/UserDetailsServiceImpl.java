package com.example.demonstration.services;

import com.example.demonstration.repositories.UserAccountRepo;
import com.example.demonstration.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepo userAccountRepo;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<UserAccount> account = userAccountRepo.findOneByUsername(username);
//        if (account.isEmpty()) {
//            throw CustomResponseException.BadCredentials();
//
//
//        }
//        UserAccount user = account.get();
//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole())
//                .build();
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userAccountRepo.findOneByUsername(username)
                .orElseThrow(CustomResponseException::BadCredentials);
    }
}
