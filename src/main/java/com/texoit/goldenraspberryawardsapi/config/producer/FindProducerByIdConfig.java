package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.FindProducerByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindProducerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProducerByIdConfig {

    @Bean
    public FindProducerByIdUseCase findProducerByIdUseCase(FindProducerByIdAdapter findProducerByIdAdapter) {
        return new FindProducerByIdUseCase(findProducerByIdAdapter);
    }

}
