package com.devoteam.demoassignments.recipemanager.jwt;

import javax.transaction.Transactional;

import com.devoteam.demoassignments.recipemanager.entity.User;
import com.devoteam.demoassignments.recipemanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with username " + username + " doesn't exist"));
        return CustomUserDetails.build(user);
    }

}