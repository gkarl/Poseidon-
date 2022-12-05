package com.nnk.springboot.service;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service use CRUD UserRepository methods to communicate whith user table
 * @see UserRepository
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    /**
     * Query all the rows of the table user table
     * @return all User items
     */
    public List<User> home(){
        logger.info("displays the users");
        return userRepository.findAll();
    }

    /**
     * Create new user
     * @param user item to save
     */
    public void validate(User user) {
        logger.info("Save a user");
        userRepository.save(user);
    }

    /**
     * Show edit form for one user using it's id
     * @param id Item id
     * @return Item whith this id
     */
    public User showUpdateForm(Integer id) {
        logger.info("Find by id from user");
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    /**
     * Update user by id
     * @param id Item id
     * @param user item to save
     */
    public void updateUser(Integer id, User user) {
        logger.info("Update a definited user by id");
        user.setId(id);
        userRepository.save(user);
    }

    /**
     * Delete user by id
     * @param id Item id
     */
    public void deleteUser(Integer id) {
        logger.info("Delete a definited user by id");
        User user = userRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        userRepository.delete(user);
    }

}
