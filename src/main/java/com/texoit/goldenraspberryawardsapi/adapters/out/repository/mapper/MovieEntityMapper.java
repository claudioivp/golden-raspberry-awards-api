package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MovieStudioEntityMapper.class, MovieProducerEntityMapper.class})
public interface MovieEntityMapper {

    MovieEntity toMovieEntity(Movie movie);
    Movie toMovie(MovieEntity movieEntity);

}
