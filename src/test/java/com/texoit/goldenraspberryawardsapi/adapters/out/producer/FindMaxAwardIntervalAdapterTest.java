package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.InsertMovieAdapter;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.InsertStudioAdapter;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class FindMaxAwardIntervalAdapterTest {

    private final FindMaxAwardIntervalAdapter findMaxAwardIntervalAdapter;
    private final InsertStudioAdapter insertStudioAdapter;
    private final InsertProducerAdapter insertProducerAdapter;
    private final InsertMovieAdapter insertMovieAdapter;
    private Movie summertimeSadness;
    private Movie aliceInWonderland;
    private Movie theFastAndTheFurious;
    private Movie theBlueLagoon;
    private Studio associatedFilmDistribution;
    private Studio universalStudios;
    private Studio newLine;
    private Producer stevenSpielberg;
    private Producer joelSilver;
    private Producer matthewVaughn;

    @Autowired
    public FindMaxAwardIntervalAdapterTest(FindMaxAwardIntervalAdapter findMaxAwardIntervalAdapter, InsertStudioAdapter insertStudioAdapter, InsertProducerAdapter insertProducerAdapter, InsertMovieAdapter insertMovieAdapter) {
        this.findMaxAwardIntervalAdapter = findMaxAwardIntervalAdapter;
        this.insertStudioAdapter = insertStudioAdapter;
        this.insertProducerAdapter = insertProducerAdapter;
        this.insertMovieAdapter = insertMovieAdapter;
    }

    @BeforeEach
    void setUp() {
        // Given Studios
        associatedFilmDistribution = new Studio(
                UUID.randomUUID(),
                "Associated Film Distribution"
        );
        universalStudios = new Studio(
                UUID.randomUUID(),
                "Universal Studios"
        );
        newLine = new Studio(
                UUID.randomUUID(),
                "New Line"
        );

        // Given Producer
        stevenSpielberg = new Producer(
                UUID.randomUUID(),
                "Steven Spielberg"
        );
        joelSilver = new Producer(
                UUID.randomUUID(),
                "Joel Silver"
        );
        matthewVaughn = new Producer(
                UUID.randomUUID(),
                "Matthew Vaughn"
        );

        // Given Movie
        summertimeSadness = new Movie(
                UUID.randomUUID(),
                1990,
                "Summertime Sadness",
                new LinkedHashSet<>(Set.of(associatedFilmDistribution, universalStudios)),
                new LinkedHashSet<>(Set.of(stevenSpielberg, joelSilver)),
                Boolean.TRUE
        );
        aliceInWonderland = new Movie(
                UUID.randomUUID(),
                2010,
                "Alice in Wonderland",
                new LinkedHashSet<>(Set.of(associatedFilmDistribution)),
                new LinkedHashSet<>(Set.of(matthewVaughn)),
                Boolean.TRUE
        );
        theFastAndTheFurious = new Movie(
                UUID.randomUUID(),
                2011,
                "The Fast and the Furious",
                new LinkedHashSet<>(Set.of(universalStudios)),
                new LinkedHashSet<>(Set.of(joelSilver, matthewVaughn)),
                Boolean.TRUE
        );
        theBlueLagoon = new Movie(
                UUID.randomUUID(),
                2020,
                "The Blue Lagoon",
                new LinkedHashSet<>(Set.of(newLine, universalStudios)),
                new LinkedHashSet<>(Set.of(joelSilver)),
                Boolean.TRUE
        );
    }

    @Test
    @DisplayName("FindMinAwardIntervalAdapter, InsertStudioAdapter, InsertProducerAdapter e InsertMovieAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(findMaxAwardIntervalAdapter);
        assertNotNull(insertStudioAdapter);
        assertNotNull(insertProducerAdapter);
        assertNotNull(insertMovieAdapter);
    }

    @Test
    @DisplayName("FindMaxAwardIntervalAdapter deve pesquisar o maior intervalo entre dois prêmios de um produtor")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindMinAwardInterval() {
        // When
        Arrays.asList(associatedFilmDistribution, universalStudios, newLine).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);
        Arrays.asList(summertimeSadness, aliceInWonderland, theFastAndTheFurious, theBlueLagoon).forEach(insertMovieAdapter::insert);

        var maxAwardIntervals = findMaxAwardIntervalAdapter.findAll();

        // Then
        assertEquals(1, maxAwardIntervals.size());
        var savedAwardInterval = maxAwardIntervals.iterator().next();
        assertEquals(joelSilver.getName(), savedAwardInterval.getProducer());
        int minIntervalExpectedInYears = theBlueLagoon.getYear() - summertimeSadness.getYear();
        assertEquals(minIntervalExpectedInYears, savedAwardInterval.getYearInterval());
        assertEquals(summertimeSadness.getYear(), savedAwardInterval.getPreviousWin());
        assertEquals(theBlueLagoon.getYear(), savedAwardInterval.getFollowingWin());
    }
}