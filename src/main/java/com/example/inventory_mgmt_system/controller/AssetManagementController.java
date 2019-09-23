package com.example.inventory_mgmt_system.controller;

import com.example.inventory_mgmt_system.data.model.Asset;
import com.example.inventory_mgmt_system.service.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON)
@Api
public class AssetManagementController {

    private static final Logger log = Logger.getLogger(AssetManagementController.class.getName());
    @Autowired
    private AssetService assetService;

    @ApiOperation(value =" Get All assets")
    @GetMapping(path = "/getAllAssetsByOrganization", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object getAllAssetsByOrganization(@RequestHeader(value="owner_id") @NotNull int owner_id) {
        log.log(Level.INFO, "Finding all Assets starts");
        return assetService.findAllAssets(owner_id).getEntity();
    }

    @ApiOperation(value =" Get assets by name")
    @GetMapping(path = "/getAllAssetsByName", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object getAllAssetsByName(@RequestParam @NotNull String searchString) {
        log.log(Level.INFO, "Finding all asset by name starts");
        return assetService.findAssetByName(searchString).getEntity();
    }

    @ApiOperation(value =" Get assets order by a given criteria")
    @GetMapping(path = "/getAllAssetsOrderByCriteria", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object getAllAssetsOrderByCriteria(@RequestParam @NotNull String orderingField, @RequestParam @NotNull int pageNo, @RequestParam @NotNull int pageSize) {
        log.log(Level.INFO, "Finding all asset by sorting and paging starts");
        return assetService.getAllAssetsOrderByCriteria(pageNo, pageSize, orderingField).getEntity();
    }

    @ApiOperation(value ="Add Asset")
    @PostMapping(path = "/addAsset", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object addAsset(@RequestHeader(value="owner_id") @NotNull int owner_id, @RequestBody @NotNull Asset asset) {
        log.log(Level.INFO, "Adding an asset  starts");
       return assetService.saveAsset(asset, owner_id).getEntity();
    }

    @ApiOperation(value ="Update an Asset")
    @PutMapping(path = "/updateAsset", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object updateAsset(@RequestHeader(value="owner_id") @NotNull int owner_id, @RequestBody @NotNull Asset asset) {
        log.log(Level.INFO, "Updating an asset starts");
        return assetService.updateAsset(owner_id, asset).getEntity();
    }


    @ApiOperation(value ="Register an Asset")
    @PutMapping(path = "/registerAsset", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object registerAsset(@RequestHeader(value="owner_id") @NotNull int owner_id, @RequestHeader(value="user_id") @NotNull int user_id, @RequestHeader(value="asset_id") @NotNull int asset_id) {
        log.log(Level.INFO, "Registering an asset starts");
        return assetService.registerAsset(owner_id, user_id, asset_id).getEntity();
    }

    @ApiOperation(value ="DeRegisterAsset an Asset")
    @PutMapping(path = "/deRegisterAsset", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object deRegisterAsset(@RequestHeader(value="user_id") @NotNull int user_id, @RequestHeader(value="asset_id") @NotNull int asset_id) {
        log.log(Level.INFO, "deRegistering an asset starts");
        return assetService.deRegisterAsset(user_id, asset_id).getEntity();
    }

    @ApiOperation(value ="Remove an Asset")
    @DeleteMapping(path = "/removeAsset", consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Object removeAsset(@RequestHeader(value="owner_id") @NotNull int owner_id, @RequestHeader(value="asset_id") @NotNull int asset_id) {
        log.log(Level.INFO, "Removing an asset starts");
        return assetService.removeAsset(owner_id, asset_id).getEntity();

    }


}
