package com.example.inventory_mgmt_system.repository;


import com.example.inventory_mgmt_system.data.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {


}
