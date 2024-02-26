package com.texoit.goldenraspberryawardsapi.application.ports.in.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;

public interface InsertMovieInputPort {

    Movie insert(Movie movie);

}
