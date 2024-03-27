package com.texoit.goldenraspberryawardsapi.config.csv;

import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidDomainException;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.ProcessCSVFileInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
@Configuration
public class CommandLineRunnerForProcessCSVConfig implements CommandLineRunner {

    private final ProcessCSVFileInputPort processCSVFileInputPort;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerForProcessCSVConfig.class);

    public CommandLineRunnerForProcessCSVConfig(ProcessCSVFileInputPort processCSVFileInputPort) {
        this.processCSVFileInputPort = processCSVFileInputPort;
    }

    @Override
    public void run(String... args) {
        logger.info("CSV file processing is being started...");

        try {
            Path filePath = Paths.get(
                    new ClassPathResource("movielist.csv").getURI()
            );

            CSVFileReaderConfig configuration =  CSVFileReaderConfig.valueOf(
                    ';',
                    1
            );

            processCSVFileInputPort.start(filePath, configuration);

            logger.info("CSV file processing completed successfully.");
        } catch (IOException | InvalidDomainException e) {
            logger.info("CSV file processing found an error: " + e.getMessage().replace("\n", ""));
            logger.info("CSV file processing was not completed successfully.");
        }
    }

}
