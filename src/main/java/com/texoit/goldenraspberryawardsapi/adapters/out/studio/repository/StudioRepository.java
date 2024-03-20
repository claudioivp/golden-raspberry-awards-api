package com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.base.repository.RefreshableJpaRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;
import java.util.UUID;

public interface StudioRepository extends RefreshableJpaRepository<StudioEntity, UUID> {

    @Cacheable(value="studios", unless = "#result == null")
    Optional<StudioEntity> findByName(String name);

}
