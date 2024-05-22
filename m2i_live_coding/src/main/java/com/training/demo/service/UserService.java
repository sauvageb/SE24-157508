package com.training.demo.service;

import com.training.demo.repository.User;
import com.training.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers() {
        return (List<User>) userRepository.findAll();

        //var u1 = new User("John", "Doe", "johndoe", LocalDate.now());
        //var u2 = new User("Jane", "Dae", "janedoe", LocalDate.now());
        //return List.of(u1, u2);
    }


}
