package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindStudioByNameAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindStudioByNameOrSaveUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertStudioInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudioByNameOrSaveConfig {

    @Bean
    public FindStudioByNameOrSaveUseCase findStudioByNameOrSaveUseCase(FindStudioByNameAdapter findStudioByNameAdapter, InsertStudioInputPort insertStudioInputPort
    ) {
        return new FindStudioByNameOrSaveUseCase(findStudioByNameAdapter, insertStudioInputPort);
    }

}
