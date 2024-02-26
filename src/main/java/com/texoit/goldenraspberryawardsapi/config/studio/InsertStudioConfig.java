package com.texoit.goldenraspberryawardsapi.config.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.InsertStudioAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.studio.InsertStudioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertStudioConfig {

    @Bean
    public InsertStudioUseCase insertStudioUseCase(InsertStudioAdapter insertStudioAdapter) {
        return new InsertStudioUseCase(insertStudioAdapter);
    }

}
