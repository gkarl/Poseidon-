package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public List<User> home(){
        logger.info("displays the users");
        return userRepository.findAll();
    }

    public void validate(User user) {
        logger.info("Save a user");
        userRepository.save(user);
    }

    public User showUpdateForm(Integer id) {
        logger.info("Find by id from bidList");
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    public void updateUser(Integer id, User user) {
        logger.info("Update a definited user by id");
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        logger.info("Delete a definited user by id");
        User user = userRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        userRepository.delete(user);
    }

}
