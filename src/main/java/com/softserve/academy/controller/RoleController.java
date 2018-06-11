package com.softserve.academy.controller;

import com.softserve.academy.model.Role;
import com.softserve.academy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Role getRoleById(@PathVariable int id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Role> getAllRoles() throws IOException {
        return (List<Role>) roleService.getAllRoles();
    }
}