package com.texoit.goldenraspberryawardsapi.application.core.usecase.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.InsertMovieInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.movie.InsertMovieOutputPort;

public class InsertMovieUseCase implements InsertMovieInputPort {

    private final InsertMovieOutputPort insertMovieOutputPort;

    public InsertMovieUseCase(InsertMovieOutputPort insertMovieOutputPort) {
        this.insertMovieOutputPort = insertMovieOutputPort;
    }

    @Override
    public Movie insert(Movie movie){
        return insertMovieOutputPort.insert(movie);
    }

}
