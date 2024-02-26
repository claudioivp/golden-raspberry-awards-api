package com.texoit.goldenraspberryawardsapi.config.csv;

import com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv.OpenCSVFileReader;
import com.texoit.goldenraspberryawardsapi.application.core.usecase.csv.ProcessCSVFileUseCase;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindProducerByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.FindStudioByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.InsertMovieInputPort;
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
