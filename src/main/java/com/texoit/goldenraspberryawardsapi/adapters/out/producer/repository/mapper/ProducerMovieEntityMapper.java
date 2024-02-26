package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProducerMovieEntityMapper {

    @Mapping(target = "producers", ignore = true)
    MovieEntity toMovieEntity(Movie movie);
    @Mapping(target = "producers", ignore = true)
    Movie toMovie(MovieEntity movieEntity);

}
