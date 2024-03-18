package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.InsertProducerAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.InsertProducerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertProducerConfig {

    @Bean
    public InsertProducerUseCase insertProducerUseCase(InsertProducerAdapter insertProducerAdapter) {
        return new InsertProducerUseCase(insertProducerAdapter);
    }
    
}
