package com.myhome.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 32)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "real_estate", nullable = false)
    private RealEstate realEstate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service", nullable = false)
    private ServiceType service;

    @Column(name = "price", nullable = false)
    private Long price;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "images", length = 32)
    private String images;

    @Column(name = "published_on", nullable = false)
    private Instant publishedOn;

}