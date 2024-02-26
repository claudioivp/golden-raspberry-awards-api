package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request.MovieRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.response.MovieResponse;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.mapper.StudioMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper.MovieProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudioMapper.class, MovieProducerEntityMapper.class})
public interface MovieMapper {

    Movie toMovie(MovieRequest movieRequest);

    MovieResponse toMovieResponse(Movie movie);

}
