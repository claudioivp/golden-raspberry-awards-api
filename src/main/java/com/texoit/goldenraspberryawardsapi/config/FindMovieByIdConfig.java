package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindMovieByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindMovieByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMovieByIdConfig {

    @Bean
    public FindMovieByIdUseCase findMovieByIdUseCase(FindMovieByIdAdapter findMovieByIdAdapter) {
        return new FindMovieByIdUseCase(findMovieByIdAdapter);
    }

}
