package com.texoit.goldenraspberryawardsapi.application.ports.out.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;

public interface InsertMovieOutputPort {

    Movie insert(Movie movie);

}
