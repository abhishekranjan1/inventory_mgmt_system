package com.example.inventory_mgmt_system.controller;

import com.example.inventory_mgmt_system.data.model.User;

import com.example.inventory_mgmt_system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(produces= MediaType.APPLICATION_JSON)
@Api
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @ApiOperation(value =" Get All Users")
    @GetMapping(path = "/allUsers", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object getAllUsersByOrganization(@RequestHeader(value="owner_id") @NotNull int owner_id) {
        log.log(Level.INFO, "Finding all users starts");
        return userService.findAllUsers(owner_id).getEntity();
    }

    @ApiOperation(value ="Save a User")
    @PostMapping(path = "/saveUser", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object saveUser(@RequestHeader(value="owner_id") @NotNull int owner_id, @RequestBody @NotNull User user) {
        log.log(Level.INFO, "Saving a user starts");
        return userService.saveUser(user, owner_id).getEntity();

    }


}
