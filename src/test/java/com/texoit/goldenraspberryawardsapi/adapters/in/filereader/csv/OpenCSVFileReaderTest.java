package com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv;

import com.opencsv.exceptions.CsvException;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidDomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class OpenCSVFileReaderTest {

    private final OpenCSVFileReader openCSVFileReader;
    private CSVFileReaderConfig csvFileReaderConfig;
    private Path tempFile;

    @Autowired
    public OpenCSVFileReaderTest(OpenCSVFileReader openCSVFileReader) {
        this.openCSVFileReader = openCSVFileReader;
    }

    @Test
    @DisplayName("OpenCSVFileReader não deve ser nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDependenciesNotNull() {
        assertNotNull(openCSVFileReader);
    }

    @Test
    @DisplayName("OpenCSVFileReader deve ler a partir de um arquivo CSV utilizando o separador padrão (,) e sem pular qualquer linha")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDefaultRead() throws IOException, CsvException {
        // Given
        csvFileReaderConfig = CSVFileReaderConfig.fromDefault();
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.writeString(tempFile, """
                year,title,studios,producers,winner
                1980,Can't Stop the Music,Associated Film Distribution,Allan Carr,yes
                1980,Cruising,"Lorimar Productions, United Artists",Jerry Weintraub,
                """);

        // When
        List<String[]> data = openCSVFileReader.read(tempFile, csvFileReaderConfig);

        // Then
        assertEquals(3, data.size());
        assertEquals("year", data.get(0)[0]);
        assertEquals("title", data.get(0)[1]);
        assertEquals("studios", data.get(0)[2]);
        assertEquals("producers", data.get(0)[3]);
        assertEquals("winner", data.get(0)[4]);

        assertEquals("1980", data.get(1)[0]);
        assertEquals("Can't Stop the Music", data.get(1)[1]);
        assertEquals("Associated Film Distribution", data.get(1)[2]);
        assertEquals("Allan Carr", data.get(1)[3]);
        assertEquals("yes", data.get(1)[4]);

        assertEquals("1980", data.get(2)[0]);
        assertEquals("Cruising", data.get(2)[1]);
        assertEquals("Lorimar Productions, United Artists", data.get(2)[2]);
        assertEquals("Jerry Weintraub", data.get(2)[3]);
        assertNull(data.get(2)[4]);

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("OpenCSVFileReader deve ler a partir de um arquivo CSV utilizando o separador customizado (;) e pulando a linha das colunas")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testReadWithCustomConfig() throws IOException, CsvException {
        // Given
        csvFileReaderConfig = CSVFileReaderConfig.valueOf(';', 1);
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.writeString(tempFile, """
                year;title;studios;producers;winner
                1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes
                1980;Cruising;Lorimar Productions, United Artists;Jerry Weintraub;
                """);

        // When
        List<String[]> data = openCSVFileReader.read(tempFile, csvFileReaderConfig);

        // Then
        assertEquals(2, data.size());

        assertEquals("1980", data.get(0)[0]);
        assertEquals("Can't Stop the Music", data.get(0)[1]);
        assertEquals("Associated Film Distribution", data.get(0)[2]);
        assertEquals("Allan Carr", data.get(0)[3]);
        assertEquals("yes", data.get(0)[4]);

        assertEquals("1980", data.get(1)[0]);
        assertEquals("Cruising", data.get(1)[1]);
        assertEquals("Lorimar Productions, United Artists", data.get(1)[2]);
        assertEquals("Jerry Weintraub", data.get(1)[3]);
        assertNull(data.get(1)[4]);

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("OpenCSVFileReader deve lançar uma excessão ao ler um arquivo csv inválido")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testInvalidFile() throws IOException {
        // Given
        csvFileReaderConfig = CSVFileReaderConfig.fromDefault();
        Path tempFile = Files.createTempFile("tempimage", ".png");
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(image, "png", tempFile.toFile());

        String type = Files.probeContentType(tempFile);

        // Then
        assertThrows(IOException.class, () -> {
            // When
            List<String[]> data = openCSVFileReader.read(tempFile, csvFileReaderConfig);
        });

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("OpenCSVFileReader lançar uma excessão quando não houver 5 colunas no arquivo csv")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInvalidColumnNumber() throws IOException, CsvException {
        // Given
        csvFileReaderConfig = CSVFileReaderConfig.valueOf(';', 1);
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.writeString(tempFile, """
                year;title;studios;producers;winner
                1980;Associated Film Distribution;Allan Carr;yes
                1980;Lorimar Productions, United Artists;Jerry Weintraub;
                """);

        // Then
        InvalidDomainException csvException = assertThrows(InvalidDomainException.class, () -> {
            // When
            List<String[]> data = openCSVFileReader.read(tempFile, csvFileReaderConfig);
        });
        assertEquals("Row must have five columns.", csvException.getMessage().replace("\n", ""));

        Files.deleteIfExists(tempFile);
    }
}