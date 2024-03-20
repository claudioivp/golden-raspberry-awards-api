package com.texoit.goldenraspberryawardsapi.adapters.out.movie;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper.MovieEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.ports.out.movie.InsertMovieOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InsertMovieAdapter implements InsertMovieOutputPort {

    private final MovieRepository movieRepository;

    private final MovieEntityMapper movieEntityMapper;

    public InsertMovieAdapter(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
        this.movieRepository = movieRepository;
        this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    @Transactional
    public Movie insert(Movie movie) {
        var movieEntity = movieRepository.saveAndFlush(movieEntityMapper.toMovieEntity(movie));
        movieRepository.refresh(movieEntity);
        return movieEntityMapper.toMovie(movieEntity);
    }

}
