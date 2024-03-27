package com.texoit.goldenraspberryawardsapi.application.core.usecase.csv;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidDomainException;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.ProcessCSVFileInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class ProcessCSVFileUseCaseTest {

    private final ProcessCSVFileInputPort processCSVFileInputPort;
    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    private Path filePath;
    private CSVFileReaderConfig configuration;

    @Autowired
    public ProcessCSVFileUseCaseTest(ProcessCSVFileInputPort processCSVFileInputPort, StudioRepository studioRepository, ProducerRepository producerRepository, MovieRepository movieRepository) {
        this.processCSVFileInputPort = processCSVFileInputPort;
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
        this.movieRepository = movieRepository;
    }

    @BeforeEach
    void setUp() throws URISyntaxException {
        // Given Path
        filePath = Paths.get(
                ClassLoader.getSystemResource("movielist-test.csv").toURI());

        // Given CSVFileReaderConfig
        configuration =  CSVFileReaderConfig.valueOf(
                ';',
                1
        );
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort não deve ser nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDependenciesNotNull() {
        assertNotNull(processCSVFileInputPort);
        assertNotNull(studioRepository);
        assertNotNull(producerRepository);
        assertNotNull(movieRepository);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve persistir corretamente o arquivo CSV")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRealCsvFileProcessing() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        int EXPECTED_STUDIOS = 6;
        int EXPECTED_PRODUCERS = 16;
        int EXPECTED_MOVIES = 8;
        int EXPECTED_MIN_INTERVAL = 2;
        int EXPECTED_MAX_INTERVAL = 1;

        // When
        processCSVFileInputPort.start(filePath, configuration);

        // Then
        assertEquals(EXPECTED_STUDIOS, studioRepository.count());
        assertEquals(EXPECTED_PRODUCERS, producerRepository.count());
        assertEquals(EXPECTED_MOVIES, movieRepository.count());
        List<ProducerIntervalsProjection> minIntervals = producerRepository.findMinIntervals();
        List<ProducerIntervalsProjection> maxIntervals = producerRepository.findMaxIntervals();
        assertEquals(EXPECTED_MIN_INTERVAL, minIntervals.size());
        assertEquals(EXPECTED_MAX_INTERVAL, maxIntervals.size());
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando o campo 'year' for nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingYearNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            ;Show Bar 2;Warner Bros.;Claudio Antunes, Rebecca Romney and Olivia Black;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=null, title='Show Bar 2', studios=[Warner Bros.], producers=[Claudio Antunes, Rebecca Romney, Olivia Black], winner=true} are not present"));

        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando o campo 'title' for nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingTitleNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            2019;;Warner Bros.;Claudio Antunes, Rebecca Romney and Olivia Black;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=2019, title='null', studios=[Warner Bros.], producers=[Claudio Antunes, Rebecca Romney, Olivia Black], winner=true} are not present"));

        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando a coleção 'studios' for nula")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingStudiosNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            2019;Show Bar 2;;Claudio Antunes, Rebecca Romney and Olivia Black;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=2019, title='Show Bar 2', studios=null, producers=[Claudio Antunes, Rebecca Romney, Olivia Black], winner=true} are not present"));

        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando o campo 'name' de um item da coleção 'studios' estiver em branco")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingStudiosNameNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            2019;Show Bar 2;Warner Bros.,  ;Claudio Antunes, Rebecca Romney and Olivia Black;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=2019, title='Show Bar 2', studios=[Warner Bros.,  ], producers=[Claudio Antunes, Rebecca Romney, Olivia Black], winner=true} are not present."));

        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando a coleção 'producers' for nula")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingProducersNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            2019;Show Bar 2;Warner Bros.;;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=2019, title='Show Bar 2', studios=[Warner Bros.], producers=null, winner=true} are not present"));

        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("ProcessCSVFileInputPort deve lançar uma exceção quando o campo 'name' de um item da coleção 'producers' estiver em branco")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCsvFileProcessingProducersNameNull() throws IOException, InvalidDomainException, URISyntaxException {
        // Given
        filePath = Files.createTempFile("test", ".csv");
        Files.writeString(filePath, """
            year;title;studios;producers;winner
            2019;Show Bar 2;Warner Bros.;Claudio Antunes,   and Olivia Black;yes
                """);

        // When
        var invalidBeanFromCsvException = assertThrows(
                InvalidDomainException.class,
                () -> {
                    // When
                    processCSVFileInputPort.start(filePath, configuration);
                });
        assertTrue(invalidBeanFromCsvException.getMessage().contains("One or more required attributes in the line CsvLineColumns{year=2019, title='Show Bar 2', studios=[Warner Bros.], producers=[Claudio Antunes,  , Olivia Black], winner=true} are not present"));

        Files.deleteIfExists(filePath);
    }
}