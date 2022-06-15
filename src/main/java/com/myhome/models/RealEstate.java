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
@Table(name = "real_estates")
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    private RealEstateType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @Column(name = "car_spaces", nullable = false)
    private Integer carSpaces;

    @OneToMany(mappedBy = "realEstate")
    private Set<Advertisement> advertisements = new LinkedHashSet<>();

}