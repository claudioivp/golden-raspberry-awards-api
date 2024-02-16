package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.InsertMovieAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.InsertMovieUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertMovieConfig {

    @Bean
    public InsertMovieUseCase insertMovieUseCase(InsertMovieAdapter insertMovieAdapter) {
        return new InsertMovieUseCase(insertMovieAdapter);
    }
    
}
