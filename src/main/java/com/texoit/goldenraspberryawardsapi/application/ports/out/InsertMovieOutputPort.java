package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;

public interface InsertMovieOutputPort {

    Movie insert(Movie movie);

}
