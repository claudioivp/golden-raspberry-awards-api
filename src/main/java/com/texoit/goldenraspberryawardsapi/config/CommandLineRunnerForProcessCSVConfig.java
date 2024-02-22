package com.texoit.goldenraspberryawardsapi.config;

import com.opencsv.exceptions.CsvException;
import com.texoit.goldenraspberryawardsapi.application.ports.in.ProcessCSVFileInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class CommandLineRunnerForProcessCSVConfig implements CommandLineRunner {

    private final ProcessCSVFileInputPort processCSVFileInputPort;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerForProcessCSVConfig.class);

    public CommandLineRunnerForProcessCSVConfig(ProcessCSVFileInputPort processCSVFileInputPort) {
        this.processCSVFileInputPort = processCSVFileInputPort;
    }

    @Override
    public void run(String... args) throws URISyntaxException, IOException, CsvException {
        Path filePath = Paths.get(
            ClassLoader.getSystemResource("movielist.csv").toURI());
        processCSVFileInputPort.start(filePath);

        logger.info("Processamento do arquivo concluído com sucesso.");
    }

}
