package com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.MovieRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.MovieResponse;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.MovieProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudioMapper.class, MovieProducerEntityMapper.class})
public interface MovieMapper {

    Movie toMovie(MovieRequest movieRequest);

    MovieResponse toMovieResponse(Movie movie);

}
