package com.springcrud.fullstack_backend.service;

import com.springcrud.fullstack_backend.exception.UserNotFoundException;
import com.springcrud.fullstack_backend.model.User;
import com.springcrud.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(userDetails.getUsername());
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    public void deleteAllUser(){
        userRepository.deleteAll();
    }
}
