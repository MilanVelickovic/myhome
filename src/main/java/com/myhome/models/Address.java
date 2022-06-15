package com.myhome.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country", nullable = false, length = 32)
    private String country;

    @Column(name = "city", nullable = false, length = 32)
    private String city;

    @Column(name = "street", nullable = false, length = 64)
    private String street;

    @Column(name = "number", nullable = false, length = 16)
    private String number;

    @OneToMany(mappedBy = "address")
    private Set<RealEstate> realEstates = new LinkedHashSet<>();

}