package com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.MovieRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.MovieResponse;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.MovieProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StudioMapper.class, MovieProducerEntityMapper.class})
public interface MovieMapper {

    Movie toMovie(MovieRequest movieRequest);

    MovieResponse toMovieResponse(Movie movie);

}
