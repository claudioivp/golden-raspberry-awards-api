package com.texoit.goldenraspberryawardsapi.config;

import com.opencsv.exceptions.CsvException;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper.StudioMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.application.ports.in.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class ProcessCSVFileConfig implements CommandLineRunner {

    private final ProcessCSVFileInputPort processCSVFileInputPort;

    public ProcessCSVFileConfig(ProcessCSVFileInputPort processCSVFileInputPort) {
        this.processCSVFileInputPort = processCSVFileInputPort;
    }

    @Override
    public void run(String... args) throws URISyntaxException, IOException, CsvException {
        Path filePath = Paths.get(
            ClassLoader.getSystemResource("movielist.csv").toURI());
        processCSVFileInputPort.start(filePath);
    }

}
