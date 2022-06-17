package com.myhome.repository;

import com.myhome.models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {

    List<RealEstate> findAll();

    RealEstate findAllByIdLike(Integer id);

}