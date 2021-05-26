package com.soma.hackathon.repository;

import com.soma.hackathon.entity.FishDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FishDetailsRepository extends JpaRepository<FishDetails, Long> {

    List<FishDetails> findByFishId(Long id);
}
