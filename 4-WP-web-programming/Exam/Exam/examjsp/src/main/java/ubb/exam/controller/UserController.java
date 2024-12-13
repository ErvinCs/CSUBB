package ubb.exam.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.model.User;
import ubb.exam.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private UserRepository users;
    private static UserController Controller = new UserController();

    public static UserController GetInstance() {
        return Controller;
    }

    private UserController() {
        this.users = new UserRepository();
    }


    public List<User> getUsers() {
        logger.log(Level.INFO, "Get Users: {}", this.users);
        return users.getAll();
    }

    public Optional<User> getUserById(Long id) {
        logger.log(Level.INFO, "Get User By Id: {}", id);
        return this.users.getById(id);
    }

    public Optional<User> getUserByCredentials(String username, String password) {
        logger.log(Level.INFO, "Get User By Credentials: {}, {}", username, password);
        return this.users.getByCredentials(username, password);
    }

    public Optional<User> getUserByUsername(String username) {
        logger.log(Level.INFO, "Get User by Username: {}", username);
        return this.users.getByUsername(username);
    }


    public void addUser(User user) {
        logger.log(Level.INFO, "Add User: {}", user);
        this.users.add(user);
    }


    public void deleteUser(Long id) {
        logger.log(Level.INFO, "Delete User By Id: {}", id);
        this.users.delete(id);
    }


    public void updateUser(User user) {
        logger.log(Level.INFO, "Update User: {}", user);
        this.users.update(user);
    }


}
