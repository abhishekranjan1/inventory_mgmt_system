package com.example.inventory_mgmt_system.repository;


import com.example.inventory_mgmt_system.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value="SELECT id, first_name,last_name,email,organization_id FROM  users where organization_id =?1", nativeQuery = true)
    List<User> findUsersByOrganization(int owner_id);


}
