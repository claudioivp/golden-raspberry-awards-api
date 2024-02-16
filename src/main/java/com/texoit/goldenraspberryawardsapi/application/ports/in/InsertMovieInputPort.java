package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;

public interface InsertMovieInputPort {

    Movie insert(Movie movie);

}
