package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.FindMinAwardIntervalAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindMinAwardIntervalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMinAwardIntervalConfig {

    @Bean
    public FindMinAwardIntervalUseCase findMinAwardIntervalUseCase(FindMinAwardIntervalAdapter findMinAwardIntervalAdapter) {
        return new FindMinAwardIntervalUseCase(findMinAwardIntervalAdapter);
    }

}
