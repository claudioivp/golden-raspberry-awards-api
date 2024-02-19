package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;

import java.util.UUID;

public interface FindMovieByIdOutputPort {

    Movie find(UUID id);

}
