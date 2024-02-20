package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindMinAwardIntervalAdapter;
import com.texoit.goldenraspberryawardsapi.adapters.out.FindMovieByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindMinAwardIntervalUseCase;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindMovieByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMinAwardIntervalConfig {

    @Bean
    public FindMinAwardIntervalUseCase findMinAwardIntervalUseCase(FindMinAwardIntervalAdapter findMinAwardIntervalAdapter) {
        return new FindMinAwardIntervalUseCase(findMinAwardIntervalAdapter);
    }

}
