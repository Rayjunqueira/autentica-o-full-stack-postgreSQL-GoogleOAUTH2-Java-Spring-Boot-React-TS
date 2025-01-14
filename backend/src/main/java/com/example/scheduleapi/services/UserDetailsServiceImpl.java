package com.example.scheduleapi.services;

import com.example.scheduleapi.models.UserModel;
import com.example.scheduleapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException  {
        Optional<UserModel> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            UserModel userModel = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(userModel.getEmail(), userModel.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
