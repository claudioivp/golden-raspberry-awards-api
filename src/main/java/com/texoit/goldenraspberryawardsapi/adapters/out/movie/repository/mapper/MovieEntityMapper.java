package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MovieStudioEntityMapper.class, MovieProducerEntityMapper.class})
public interface MovieEntityMapper {

    MovieEntity toMovieEntity(Movie movie);
    Movie toMovie(MovieEntity movieEntity);

}
