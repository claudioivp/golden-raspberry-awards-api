package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    Optional<MovieEntity> findByTitle(String title);

}
