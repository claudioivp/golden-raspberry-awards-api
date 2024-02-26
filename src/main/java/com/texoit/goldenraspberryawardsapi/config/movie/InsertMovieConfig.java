package com.texoit.goldenraspberryawardsapi.config.movie;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.InsertMovieAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.movie.InsertMovieUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertMovieConfig {

    @Bean
    public InsertMovieUseCase insertMovieUseCase(InsertMovieAdapter insertMovieAdapter) {
        return new InsertMovieUseCase(insertMovieAdapter);
    }
    
}
