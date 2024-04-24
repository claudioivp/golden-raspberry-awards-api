package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class AwardIntervalControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    private MovieEntity summertimeSadness;
    private MovieEntity aliceInWonderland;
    private MovieEntity theFastAndTheFurious;
    private MovieEntity theBlueLagoon;
    private StudioEntity associatedFilmDistribution;
    private StudioEntity universalStudios;
    private StudioEntity newLine;
    private ProducerEntity stevenSpielberg;
    private ProducerEntity joelSilver;
    private ProducerEntity matthewVaughn;

    @Autowired
    public AwardIntervalControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, StudioRepository studioRepository, ProducerRepository producerRepository, MovieRepository movieRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
        this.movieRepository = movieRepository;
    }

    @BeforeEach
    public void setUp() {
        // Given Studios
        associatedFilmDistribution = new StudioEntity(
                UUID.randomUUID(),
                "Associated Film Distribution"
        );
        universalStudios = new StudioEntity(
                UUID.randomUUID(),
                "Universal Studios"
        );
        newLine = new StudioEntity(
                UUID.randomUUID(),
                "New Line"
        );

        // Given Producer
        stevenSpielberg = new ProducerEntity(
                UUID.randomUUID(),
                "Steven Spielberg"
        );
        joelSilver = new ProducerEntity(
                UUID.randomUUID(),
                "Joel Silver"
        );
        matthewVaughn = new ProducerEntity(
                UUID.randomUUID(),
                "Matthew Vaughn"
        );

        // Given Movie
        summertimeSadness = new MovieEntity(
                UUID.randomUUID(),
                1990,
                "Summertime Sadness",
                new ArrayList<>(Arrays.asList(associatedFilmDistribution, universalStudios)),
                new ArrayList<>(Arrays.asList(stevenSpielberg, joelSilver)),
                Boolean.TRUE
        );
        aliceInWonderland = new MovieEntity(
                UUID.randomUUID(),
                2010,
                "Alice in Wonderland",
                new ArrayList<>(Collections.singletonList(associatedFilmDistribution)),
                new ArrayList<>(Collections.singletonList(matthewVaughn)),
                Boolean.TRUE
        );
        theFastAndTheFurious = new MovieEntity(
                UUID.randomUUID(),
                2011,
                "The Fast and the Furious",
                new ArrayList<>(Collections.singletonList(universalStudios)),
                new ArrayList<>(Arrays.asList(joelSilver, matthewVaughn)),
                Boolean.TRUE
        );
        theBlueLagoon = new MovieEntity(
                UUID.randomUUID(),
                2020,
                "The Blue Lagoon",
                new ArrayList<>(Arrays.asList(newLine, universalStudios)),
                new ArrayList<>(Collections.singletonList(joelSilver)),
                Boolean.TRUE
        );
    }

    @Test
    @DisplayName("MockMvc, ObjectMapper, StudioRepository, ProducerRepository e MovieRepository não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDependenciesNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(studioRepository);
        assertNotNull(producerRepository);
        assertNotNull(movieRepository);
    }

    @Test
    @DisplayName("AwardIntervalController retornar 200 OK, além os intervalos mínimo e máximo de prêmios consecutivos de um mesmo produtor")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindMinAwardInterval() throws Exception {
        // Given
        Arrays.asList(associatedFilmDistribution, universalStudios, newLine).forEach(studioRepository::saveAndFlush);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(producerRepository::saveAndFlush);
        Arrays.asList(summertimeSadness, aliceInWonderland, theFastAndTheFurious, theBlueLagoon).forEach(movieRepository::saveAndFlush);
        int minIntervalExpectedInYears = theFastAndTheFurious.getYear() - aliceInWonderland.getYear();
        int maxIntervalExpectedInYears = theFastAndTheFurious.getYear() - summertimeSadness.getYear();

        // When
        mockMvc.perform(get("/api/v1/awardintervals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Then
                .andExpect(jsonPath("$.min").isNotEmpty()) // Then
                .andExpect(jsonPath("$.max").isNotEmpty()) // Then
                .andExpect(jsonPath("""
                                $.min[?(@.producer=='%s' && @.yearInterval=='%s'&& @.previousWin=='%s' && @.followingWin=='%s')]
                                """
                                .formatted(
                                        matthewVaughn.getName(),
                                        minIntervalExpectedInYears,
                                        aliceInWonderland.getYear(),
                                        theFastAndTheFurious.getYear()
                                )
                        ).exists()
                ).andExpect(jsonPath("""
                                $.max[?(@.producer=='%s' && @.yearInterval=='%s'&& @.previousWin=='%s' && @.followingWin=='%s')]
                                """
                                .formatted(
                                        joelSilver.getName(),
                                        maxIntervalExpectedInYears,
                                        summertimeSadness.getYear(),
                                        theFastAndTheFurious.getYear()
                                )
                        ).exists()
                );

    }

}