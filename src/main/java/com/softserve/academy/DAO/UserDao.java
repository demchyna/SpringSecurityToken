package com.softserve.academy.DAO;

import com.softserve.academy.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends DatasourceFactory {

    public void createUser(User user) {
        getSession().save(user);
    }

    public void updateUser(User user) {
        getSession().merge(user);
    }

    public void deleteUser(User user) {
        getSession().delete(user);
    }

    public User getUserById(int id) {
        return getSession().get(User.class, id);
    }

    public User getUserByName(String username) {
        Query query = getSession().createQuery("FROM " + User.class.getName() + " where username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public List<User> getAllUsers() {
        return (List<User>)getSession().createQuery("FROM " + User.class.getName()).list();
    }
}
