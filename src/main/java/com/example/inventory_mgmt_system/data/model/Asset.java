package com.example.inventory_mgmt_system.data.model;

import com.example.inventory_mgmt_system.config.PostgreSQLEnumType;
import com.example.inventory_mgmt_system.data.constants.Asset_Type;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="assets")
@NoArgsConstructor
@TypeDef(
        name = "asset_type",
        typeClass = PostgreSQLEnumType.class
)
public class Asset {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assets_id_seq")
    @SequenceGenerator(name="assets_id_seq", sequenceName = "assets_id_seq", allocationSize=1)
    private int id;
    @Getter
    @Setter
    @Column
    private String name;
    @Getter
    @Setter
    @Column
    private String brand;
    @Getter
    @Setter
    @Column
    private String model;
    @Getter
    @Setter
    @Column
    private String serial_number;
    @Getter
    @Setter
    @Column
    private Date acquisition;
    @Getter
    @Setter
    @Column
    private Date warranty_expiration;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Type( type = "asset_type")
    @Column(columnDefinition = "asset_type")
    private Asset_Type type;
    @Getter
    @Setter
    @Column
    private int cost;
    @Getter
    @Setter
    @Column
    private boolean retired;

    @Getter
    @Setter
    //bi-directional many-to-one association to User
    @OneToMany(mappedBy ="asset")
    private List<Asset> assets;

    @Getter
    @Setter
    //bi-directional many-to-one association to User
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="asset_id")
    @JsonIgnore
    private Asset asset;




    //bi-directional many-to-one association to User
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    @JsonIgnore
    private Organization organization;

    //bi-directional many-to-one association to User
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

}
