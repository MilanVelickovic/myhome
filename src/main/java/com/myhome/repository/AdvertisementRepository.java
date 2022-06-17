package com.myhome.repository;

import com.myhome.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Advertisement getAdvertisementById(Integer id);


}