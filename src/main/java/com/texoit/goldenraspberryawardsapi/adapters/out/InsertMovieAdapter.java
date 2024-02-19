package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.MovieEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.out.InsertMovieOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertMovieAdapter implements InsertMovieOutputPort {

    private final MovieRepository movieRepository;

    private final MovieEntityMapper movieEntityMapper;

    public InsertMovieAdapter(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
        this.movieRepository = movieRepository;
        this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public Movie insert(Movie movie) {
        var movieEntity = movieRepository.save(movieEntityMapper.toMovieEntity(movie));
        return movieEntityMapper.toMovie(movieEntity);
    }

}
