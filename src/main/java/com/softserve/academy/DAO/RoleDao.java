package com.softserve.academy.DAO;

import com.softserve.academy.model.Role;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoleDao extends DatasourceFactory {
    public Role getRoleById(int id) {
        return getSession().get(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return (List<Role>)getSession().createQuery("FROM " + Role.class.getName()).list();
    }
}
