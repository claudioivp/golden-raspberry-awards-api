package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindMaxAwardIntervalAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindMaxAwardIntervalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMaxAwardIntervalConfig {

    @Bean
    public FindMaxAwardIntervalUseCase findMaxAwardIntervalUseCase(FindMaxAwardIntervalAdapter findMaxAwardIntervalAdapter) {
        return new FindMaxAwardIntervalUseCase(findMaxAwardIntervalAdapter);
    }

}
