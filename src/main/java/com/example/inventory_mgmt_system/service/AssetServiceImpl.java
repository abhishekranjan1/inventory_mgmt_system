package com.example.inventory_mgmt_system.service;

import com.example.inventory_mgmt_system.config.EhCacheManager;
import com.example.inventory_mgmt_system.data.model.Asset;
import com.example.inventory_mgmt_system.data.model.Organization;
import com.example.inventory_mgmt_system.data.model.User;
import com.example.inventory_mgmt_system.dto.ResponseDto;
import com.example.inventory_mgmt_system.exception.AssetManagerException;
import com.example.inventory_mgmt_system.repository.AssetRepository;
import com.example.inventory_mgmt_system.repository.OrganizationRepository;
import com.example.inventory_mgmt_system.util.ServiceUtils;
import com.google.gson.Gson;
import org.ehcache.core.EhcacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Response findAllAssets(int owner_id) {
      if(!serviceUtils.isValidOrganization(owner_id)){
            throw new AssetManagerException("Organization with organization_id: "+owner_id+" not found");
      }
        Object response;
        Asset asset = (Asset) EhCacheManager.getCache("asset-"+owner_id);
        if(asset != null){
           response = asset;
        }
        else {
            response = assetRepository.findAssetsByOrganization(owner_id);
        }

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @Override
    public Response saveAsset(Asset asset, int owner_id) {
        Organization owner = serviceUtils.getOrganization(owner_id);
        asset.setOrganization(owner);

        assetRepository.save(asset);
        EhCacheManager.putCache("asset-"+ owner_id, asset);
        Object message = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .build();
    }

    @Override
    public Response registerAsset(int owner_id, int user_id, int asset_id) {
        if(!serviceUtils.isValidOrganization(owner_id) ) {
            throw new AssetManagerException("The organization is not valid");
        }
            User user = serviceUtils.getUserDetailsById(user_id);
            Asset asset = serviceUtils.getValidatedAsset(owner_id, user, asset_id);
            asset.setUser(user);
            assetRepository.save(asset);
        Object message = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .build();
        }

    @Override
    public Response deRegisterAsset(int user_id, int asset_id) {
       Asset asset = serviceUtils.getAssetDetailsById(asset_id);
       if(asset.getUser()==null || asset.getUser().getId()!=user_id){
           throw new AssetManagerException("The asset does not belong to given user");
       }
       asset.setUser(null);
        assetRepository.save(asset);
        Object message = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .build();
       }

    @Override
    public Response removeAsset(int owner_id, int asset_id) {
        Asset asset = serviceUtils.getValidatedAssetForModification(owner_id, asset_id);
        assetRepository.delete(asset);
        Object message = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .build();
    }

    @Override
    public Response updateAsset(int owner_id, Asset asset) {
        serviceUtils.getValidatedAssetForModification(owner_id, asset.getId());
        Asset existingAsset = serviceUtils.getAssetDetailsById(asset.getId()) ;

        if(asset.getOrganization()==null){
            asset.setOrganization(existingAsset.getOrganization());
        }
        assetRepository.save(asset);
        Object message = new ResponseDto("Success");
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .build();
    }

    @Override
    public Response findAssetByName(String searchString) {
        List<Asset> assets = assetRepository.findByName(searchString.toLowerCase());
        Object response;
        if(assets == null){
            response = Collections.EMPTY_LIST;
        }
        else{
            response = assets;
        }
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @Override
    public Response getAllAssetsOrderByCriteria(int pageNo, int pageSize, String orderingField) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orderingField));
        Page<Asset> page = assetRepository.findAll(pageable);
        Object response;
        if(page == null){
            response = Collections.EMPTY_LIST;
        }
        else {
            response = page.getContent();
        }
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
