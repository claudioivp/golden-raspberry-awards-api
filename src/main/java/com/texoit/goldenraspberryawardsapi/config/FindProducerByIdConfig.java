package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindProducerByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindProducerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProducerByIdConfig {

    @Bean
    public FindProducerByIdUseCase findProducerByIdUseCase(FindProducerByIdAdapter findProducerByIdAdapter) {
        return new FindProducerByIdUseCase(findProducerByIdAdapter);
    }

}
