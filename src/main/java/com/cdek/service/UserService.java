package com.cdek.service;


import com.cdek.domain.User;
import com.cdek.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByName(user.getName());

        if (userFromDb != null && userFromDb.getName().equals(user.getName())){
            return false;
        }

        userRepository.save(user);

        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUser(String username){
        return userRepository.findByName(username);
    }
}
