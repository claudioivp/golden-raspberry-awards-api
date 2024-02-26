package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.FindProducerByNameAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindProducerByNameOrSaveUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.InsertProducerInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProducerByNameOrSaveConfig {

    @Bean
    public FindProducerByNameOrSaveUseCase findProducerByNameOrSaveUseCase(FindProducerByNameAdapter findProducerByNameAdapter, InsertProducerInputPort insertProducerInputPort
    ) {
        return new FindProducerByNameOrSaveUseCase(findProducerByNameAdapter, insertProducerInputPort);
    }

}
