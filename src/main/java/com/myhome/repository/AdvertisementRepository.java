package com.myhome.repository;

import com.myhome.models.Advertisement;
import com.myhome.models.RealEstate;
import com.myhome.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Optional<List<Advertisement>> findByUser(User user);

    Optional<Advertisement> findByPublishedOn(Instant instant);

    Optional<Advertisement> findByRealEstate(RealEstate realEstate);

}