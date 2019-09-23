package com.example.inventory_mgmt_system.service;

import com.example.inventory_mgmt_system.data.model.Asset;

import javax.ws.rs.core.Response;

public interface AssetService {
    Response findAllAssets(int owner_id);

    Response saveAsset(Asset asset, int owner_id);

    Response registerAsset(int owner_id, int user_id, int asset_id);

    Response deRegisterAsset(int user_id, int asset_id);

    Response removeAsset(int owner_id, int asset_id);

    Response updateAsset(int owner_id, Asset asset);

    Response findAssetByName(String searchString);

    Response getAllAssetsOrderByCriteria(int pageNo, int pageSize, String orderingField);
}
