package com.texoit.goldenraspberryawardsapi.adapters.out.movie;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.InsertProducerAdapter;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class FindMovieByIdAdapterTest {

    private final FindMovieByIdAdapter findMovieByIdAdapter;
    private final InsertStudioAdapter insertStudioAdapter;
    private final InsertProducerAdapter insertProducerAdapter;
    private final InsertMovieAdapter insertMovieAdapter;
    private Movie movieToSave;
    private Studio associatedFilmDistribution;
    private Studio universalStudios;
    private Producer stevenSpielberg;
    private Producer joelSilver;
    private Producer matthewVaughn;

    @Autowired
    public FindMovieByIdAdapterTest(FindMovieByIdAdapter findMovieByIdAdapter, InsertStudioAdapter insertStudioAdapter, InsertProducerAdapter insertProducerAdapter, InsertMovieAdapter insertMovieAdapter) {
        this.findMovieByIdAdapter = findMovieByIdAdapter;
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
        movieToSave = new Movie(
                UUID.randomUUID(),
                1990,
                "Movie Test",
                new ArrayList<>(Arrays.asList(associatedFilmDistribution, universalStudios)),
                new ArrayList<>(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn)),
                Boolean.TRUE
        );
    }

    @Test
    @DisplayName("FindMovieByIdAdapter, InsertMovieAdapter, InsertProducerAdapter e InsertStudioAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void repositoryLoads() {
        assertNotNull(findMovieByIdAdapter);
        assertNotNull(insertMovieAdapter);
        assertNotNull(insertProducerAdapter);
        assertNotNull(insertStudioAdapter);
    }

    @Test
    @DisplayName("MovieRepository deve recuperar uma entidade pelo campo 'id'")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByIdMovie() {
        // When
        Arrays.asList(associatedFilmDistribution, universalStudios).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);
        Movie savedMovie = insertMovieAdapter.insert(movieToSave);
        var retrievedMovie = findMovieByIdAdapter.find(savedMovie.getId());

        // Then
        assertEquals(movieToSave.getId(), retrievedMovie.getId());
        assertEquals(movieToSave.getTitle(), retrievedMovie.getTitle());
    }
}