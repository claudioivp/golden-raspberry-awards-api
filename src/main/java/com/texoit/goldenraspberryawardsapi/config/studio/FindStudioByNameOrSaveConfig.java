package com.texoit.goldenraspberryawardsapi.config.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.FindStudioByNameAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.studio.FindStudioByNameOrSaveUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.InsertStudioInputPort;
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
