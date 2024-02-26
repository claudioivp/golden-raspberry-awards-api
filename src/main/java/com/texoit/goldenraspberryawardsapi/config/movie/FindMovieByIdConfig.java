package com.texoit.goldenraspberryawardsapi.config.movie;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.FindMovieByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.movie.FindMovieByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMovieByIdConfig {

    @Bean
    public FindMovieByIdUseCase findMovieByIdUseCase(FindMovieByIdAdapter findMovieByIdAdapter) {
        return new FindMovieByIdUseCase(findMovieByIdAdapter);
    }

}
