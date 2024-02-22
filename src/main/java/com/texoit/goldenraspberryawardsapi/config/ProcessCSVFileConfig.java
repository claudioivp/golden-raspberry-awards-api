package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.adapters.in.filereader.OpenCSVFileReader;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.ProcessCSVFileUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindProducerByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindStudioByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertMovieInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessCSVFileConfig {

    @Bean
    public ProcessCSVFileUseCase processCSVFileUseCase(
            OpenCSVFileReader openCSVFileReader,
            FindStudioByNameOrSaveInputPort findStudioByNameOrSaveInputPort,
            FindProducerByNameOrSaveInputPort InsertProducerInputPort,
            InsertMovieInputPort insertMovieInputPort
    ) {
        return new ProcessCSVFileUseCase(openCSVFileReader, findStudioByNameOrSaveInputPort, InsertProducerInputPort, insertMovieInputPort);
    }

}
