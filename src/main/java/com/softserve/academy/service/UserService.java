package com.softserve.academy.service;


import com.softserve.academy.DAO.UserDao;
import com.softserve.academy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    @Autowired UserDao userDao;

    @Transactional
    public void createUser(User user) {
        User extUser = userDao.getUserById(user.getId());
        if (extUser == null) {
            userDao.createUser(user);
        } else {
            System.err.println("Record already exists in database");
        }
    }

    @Transactional
    public void updateUser(User user) {
        User updUser = userDao.getUserById(user.getId());
        if (updUser != null) {
            userDao.updateUser(user);
        } else {
            System.err.println("Record with id " + user.getId()  +" not found in database");
        }
    }

    @Transactional
    public void deleteUser(User user) {
        User delUser = userDao.getUserById(user.getId());
        if (delUser != null) {
            userDao.deleteUser(delUser);
        } else {
            System.err.println("Record with id " + user.getId()  +" not found in database");
        }
    }

    @Transactional
    public User getUserById(int id) {
        User user = userDao.getUserById(id);
        if (user != null) {
            return user;
        } else {
            System.err.println("Record with id " + id  +" not found in database");
        }
        return null;
    }

    @Transactional
    @Override
    public User loadUserByUsername(String username) {
        User user = userDao.getUserByName(username);
        if (user != null) {
            return user;
        } else {
            System.err.println("Record with username " + username  +" not found in database");
        }
        return null;
    }

    @Transactional
    public List getAllUsers() {
        List<User> users = userDao.getAllUsers();
        if (!users.isEmpty()) {
            return users;
        } else {
            System.err.println("Table '" + (User.class).getSimpleName().toLowerCase() + "' in database is empty");
        }
        return null;
    }
}