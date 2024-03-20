package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.base.repository.RefreshableJpaRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends RefreshableJpaRepository<MovieEntity, UUID> {

    Optional<MovieEntity> findByTitle(String title);

}
