package com.texoit.goldenraspberryawardsapi.adapters.out.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudioRepository extends JpaRepository<StudioEntity, UUID> {

    @Cacheable(value="studios", unless = "#result == null")
    Optional<StudioEntity> findByName(String name);

}
