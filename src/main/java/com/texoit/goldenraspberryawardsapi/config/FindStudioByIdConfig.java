package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.out.FindStudioByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindStudioByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudioByIdConfig {

    @Bean
    public FindStudioByIdUseCase findStudioByIdUseCase(FindStudioByIdAdapter findStudioByIdAdapter) {
        return new FindStudioByIdUseCase(findStudioByIdAdapter);
    }

}
