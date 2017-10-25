package com.softserve.academy.service;

import com.softserve.academy.DAO.RoleDao;
import com.softserve.academy.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired RoleDao roleDao;

    @Transactional
    public Role getRoleById(int id) {
        Role role = roleDao.getRoleById(id);
        if (role != null) {
            return role;
        } else {
            System.err.println("Record with id " + id  +" not found in database");
        }
        return null;
    }

    @Transactional
    public List getAllRoles() {
        List<Role> roles = roleDao.getAllRoles();
        if (!roles.isEmpty()) {
            return roles;
        } else {
            System.err.println("Table '" + (Role.class).getSimpleName().toLowerCase() + "' in database is empty");
        }
        return null;
    }
}
