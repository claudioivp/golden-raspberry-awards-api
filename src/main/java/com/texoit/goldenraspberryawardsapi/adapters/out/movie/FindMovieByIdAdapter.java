package com.texoit.goldenraspberryawardsapi.adapters.out.movie;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper.MovieEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.out.movie.FindMovieByIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FindMovieByIdAdapter implements FindMovieByIdOutputPort {

    private final MovieRepository movieRepository;
    private final MovieEntityMapper movieEntityMapper;

    public FindMovieByIdAdapter(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
        this.movieRepository = movieRepository;
        this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public Movie find(UUID id) {
        var movieEntity = movieRepository.getReferenceById(id);
        return movieEntityMapper.toMovie(movieEntity);
    }
}
