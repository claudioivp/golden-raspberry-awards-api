package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindProducerByNameAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindProducerByNameOrSaveUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertProducerInputPort;
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
