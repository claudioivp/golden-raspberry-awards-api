package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProducerMovieEntityMapper {

    @Mapping(target = "producers", ignore = true)
    MovieEntity toMovieEntity(Movie movie);
    @Mapping(target = "producers", ignore = true)
    Movie toMovie(MovieEntity movieEntity);

}
