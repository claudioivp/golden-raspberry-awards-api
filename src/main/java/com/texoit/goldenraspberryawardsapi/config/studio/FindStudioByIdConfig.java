package com.texoit.goldenraspberryawardsapi.config.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.FindStudioByIdAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.studio.FindStudioByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudioByIdConfig {

    @Bean
    public FindStudioByIdUseCase findStudioByIdUseCase(FindStudioByIdAdapter findStudioByIdAdapter) {
        return new FindStudioByIdUseCase(findStudioByIdAdapter);
    }

}
