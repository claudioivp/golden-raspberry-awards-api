package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;

import java.util.UUID;

public interface FindMovieByIdInputPort {

    Movie find(UUID id);

}
