package com.texoit.goldenraspberryawardsapi.application.core.usecase.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.FindMovieByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.movie.FindMovieByIdOutputPort;

import java.util.UUID;

public class FindMovieByIdUseCase implements FindMovieByIdInputPort {

    private final FindMovieByIdOutputPort findMovieByIdOutputPort;

    public FindMovieByIdUseCase(FindMovieByIdOutputPort findMovieByIdOutputPort) {
        this.findMovieByIdOutputPort = findMovieByIdOutputPort;
    }

    @Override
    public Movie find(UUID id) {
        return findMovieByIdOutputPort.find(id);
    }
}
