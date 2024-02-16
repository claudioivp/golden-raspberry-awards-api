package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertMovieInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.InsertMovieOutputPort;

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
