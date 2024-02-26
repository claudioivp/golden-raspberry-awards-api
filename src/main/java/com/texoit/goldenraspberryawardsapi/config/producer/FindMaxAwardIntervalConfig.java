package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.FindMaxAwardIntervalAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindMaxAwardIntervalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMaxAwardIntervalConfig {

    @Bean
    public FindMaxAwardIntervalUseCase findMaxAwardIntervalUseCase(FindMaxAwardIntervalAdapter findMaxAwardIntervalAdapter) {
        return new FindMaxAwardIntervalUseCase(findMaxAwardIntervalAdapter);
    }

}
