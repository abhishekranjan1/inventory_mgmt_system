package com.example.inventory_mgmt_system.repository;


import com.example.inventory_mgmt_system.data.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AssetRepository  extends PagingAndSortingRepository<Asset,Integer> {


    @Query(value="SELECT * FROM  assets where owner_id = ?1", nativeQuery = true)
    List<Asset> findAssetsByOrganization(int owner_id);

    @Query(value="SELECT * FROM  assets where lower(name) like %?1%", nativeQuery = true)
    List<Asset> findByName(String searchString);

    Page<Asset> findAll(Pageable pageable);
}
