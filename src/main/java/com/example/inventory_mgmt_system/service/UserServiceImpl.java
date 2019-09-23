package com.example.inventory_mgmt_system.service;

import com.example.inventory_mgmt_system.data.model.Organization;
import com.example.inventory_mgmt_system.data.model.User;

import com.example.inventory_mgmt_system.dto.ResponseDto;
import com.example.inventory_mgmt_system.repository.OrganizationRepository;
import com.example.inventory_mgmt_system.repository.UserRepository;
import com.example.inventory_mgmt_system.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public Response findAllUsers(int owner_id) {

        //return userRepository.findAll();

        Organization organization = serviceUtils.getOrganization(owner_id);
        Object response = userRepository.findUsersByOrganization(owner_id);

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();

    }

    @Override
    public Response saveUser(User user, int owner_id) {
        Organization owner = serviceUtils.getOrganization(owner_id);
        user.setOrganization(owner);

        userRepository.save(user);
        Object response = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }


}
