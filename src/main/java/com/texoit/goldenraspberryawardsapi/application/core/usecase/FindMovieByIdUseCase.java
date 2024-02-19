package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindMovieByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindMovieByIdOutputPort;

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
