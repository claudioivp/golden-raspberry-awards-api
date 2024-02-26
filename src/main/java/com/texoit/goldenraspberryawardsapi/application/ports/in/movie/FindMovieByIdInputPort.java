package com.texoit.goldenraspberryawardsapi.application.ports.in.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;

import java.util.UUID;

public interface FindMovieByIdInputPort {

    Movie find(UUID id);

}
