package com.soma.hackathon.repository;

import com.soma.hackathon.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FishRepository extends JpaRepository<Fish, Long> {
    List<Fish> findByFishNameIsContaining(String fishName);

    Fish findOneByFishCode(String fishCode);
}
