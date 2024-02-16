package com.texoit.goldenraspberryawardsapi.adapters.out.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
}
