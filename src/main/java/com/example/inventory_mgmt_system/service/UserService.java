package com.example.inventory_mgmt_system.service;

import com.example.inventory_mgmt_system.data.model.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public interface UserService {
    Response findAllUsers(int owner_id);

    Response saveUser(User user, int owner_id);
}
