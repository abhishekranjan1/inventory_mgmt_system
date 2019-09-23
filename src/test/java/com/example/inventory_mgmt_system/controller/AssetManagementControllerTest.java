package com.example.inventory_mgmt_system.controller;

import com.example.inventory_mgmt_system.InventoryMgmtSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AssetManagementController.class, InventoryMgmtSystemApplication.class })
public class AssetManagementControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders requestHeaders = new HttpHeaders();

    @Test
    public void getAllAssetsByOrganization() {
    }

    @Test
    public void getAllAssetsByName() {
    }

    @Test
    public void getAllAssetsOrderByCriteria() {
    }

    @Test
    public void addAsset() {
    }

    @Test
    public void updateAsset() {
    }

    @Test
    public void registerAsset() {
    }

    @Test
    public void deRegisterAsset() {
    }

    @Test
    public void removeAsset() {
    }
}