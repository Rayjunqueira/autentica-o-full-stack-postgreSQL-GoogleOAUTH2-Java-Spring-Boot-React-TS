package com.example.scheduleapi.services;

import com.example.scheduleapi.models.UserModel;
import com.example.scheduleapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel save (UserModel userModel) {
        return userRepository.save(userModel);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }
}
