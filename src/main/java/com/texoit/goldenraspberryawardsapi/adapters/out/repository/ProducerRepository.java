package com.texoit.goldenraspberryawardsapi.adapters.out.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProducerRepository extends JpaRepository<ProducerEntity, UUID> {

    @Cacheable(value="producers", unless = "#result == null")
    Optional<ProducerEntity> findByName(String name);

}
