package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StudioEntityMapper.class, ProducerEntityMapper.class})
public interface MovieEntityMapper {

    @Mapping(target = "producers", ignore = true)
    MovieEntity toMovieEntity(Movie movie);

    ProducerEntityMapper producerEntityMapper = Mappers.getMapper(ProducerEntityMapper.class);

    @AfterMapping
    default void mapProducers(Movie movie, @MappingTarget MovieEntity movieEntity) {
        movieEntity.setProducers(
                movie.getProducers().stream()
                        .map(producerEntityMapper::toProducerEntity)
                        .collect(Collectors.toSet())
        );
    }

    Movie toMovie(MovieEntity movieEntity);
}
