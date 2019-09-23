package com.example.inventory_mgmt_system.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="organizations")
public class Organization {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizations_id_seq")
    @SequenceGenerator(name="organizations_id_seq", sequenceName = "organizations_id_seq",allocationSize=1)
    private int id;
    @Getter
    @Setter
    @Column
    private String name;
    @Getter
    @Setter
    @Column
    private String address;

    @Getter
    @Setter
    //bi-directional many-to-one association to Asset
    @OneToMany(mappedBy = "organization")
    //@JoinTable(joinColumns = @JoinColumn(name ="owner_id"))
    private List<Asset> assets;

    @Getter
    @Setter
    //bi-directional many-to-one association to User
    @OneToMany(mappedBy = "organization")
    private List<User> users;


}
