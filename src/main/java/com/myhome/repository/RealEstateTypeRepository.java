package com.myhome.repository;

import com.myhome.models.RealEstateType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateTypeRepository extends JpaRepository<RealEstateType, Integer> {
}