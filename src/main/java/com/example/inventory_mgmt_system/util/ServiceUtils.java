package com.example.inventory_mgmt_system.util;

import com.example.inventory_mgmt_system.data.model.Asset;
import com.example.inventory_mgmt_system.data.model.Organization;
import com.example.inventory_mgmt_system.data.model.User;
import com.example.inventory_mgmt_system.exception.AssetManagerException;
import com.example.inventory_mgmt_system.repository.AssetRepository;
import com.example.inventory_mgmt_system.repository.OrganizationRepository;
import com.example.inventory_mgmt_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceUtils {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;


    public Organization getOrganization(int owner_id) {
        Optional<Organization> option = organizationRepository.findById(owner_id);

        if(!option.isPresent()){
            throw new AssetManagerException("Organization with organization_id: "+owner_id+" not found");
        }
        return option.get();
    }

    public boolean isValidOrganization(int owner_id) {
        Optional<Organization> option = organizationRepository.findById(owner_id);
        if(!option.isPresent()){
            return false;
        }
        return true;
    }

    public User getUserDetailsById(int user_id) {

        Optional<User> option = userRepository.findById(user_id);
        if(!option.isPresent()){
            throw new AssetManagerException("User with user_id: "+user_id+" not found");
        }
        return option.get();
    }

    public Asset getValidatedAsset(int owner_id, User user, int asset_id) {

        if(user.getOrganization().getId()!=owner_id)
        {
            throw new AssetManagerException("User does not belong to organization");
        }
        Asset asset = getAssetDetailsById(asset_id);
        if(asset.getOrganization()==null || asset.getOrganization().getId()!=owner_id){
            throw new AssetManagerException("Asset does not belong to organization");
        }
        if(asset.getUser()!=null){
            throw new AssetManagerException("Asset is already assigned, please check again later!");
        }
        return asset;
    }

    public Asset getAssetDetailsById(int asset_id) {
        Optional<Asset> option =assetRepository.findById(asset_id);
        if(!option.isPresent()){
            throw new AssetManagerException("Asset with asset_id: "+asset_id+" not found");
        }
        return option.get();
    }

    public boolean isAssetPresent(int asset_id) {
        Optional<Asset> option =assetRepository.findById(asset_id);
        if(!option.isPresent()){
           return false;        }
        return true;
    }

        public Asset getValidatedAssetForModification(int owner_id, int asset_id) {
        if(!isValidOrganization(owner_id)){
            throw new AssetManagerException("Invalid organization");
        }
        Asset asset = getAssetDetailsById(asset_id);
        if(asset.getUser()!=null){
            throw new AssetManagerException("This asset is assigned to someone, so we cant change it's status!");
        }
        if(asset.getOrganization().getId()!=owner_id){
            throw new AssetManagerException("This asset does not belong to the organization mentioned!");
        }
        return asset;

    }
}
