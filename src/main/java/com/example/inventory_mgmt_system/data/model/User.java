package com.example.inventory_mgmt_system.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")

public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName = "users_id_seq", allocationSize=1)
    private int id;
    @Getter
    @Setter
    @Column
    private String first_name;
    @Getter
    @Setter
    @Column
    private String last_name;
    @Getter
    @Setter
    @Column
    private String email;

    //bi-directional many-to-one association to Asset
    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
     private List<Asset> assets;

    @Getter
    @Setter
    //bi-directional many-to-one association to Organization
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @JsonIgnore
    private Organization organization;

}
