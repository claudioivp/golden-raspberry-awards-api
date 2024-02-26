package com.texoit.goldenraspberryawardsapi.application.ports.out.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;

import java.util.UUID;

public interface FindMovieByIdOutputPort {

    Movie find(UUID id);

}
