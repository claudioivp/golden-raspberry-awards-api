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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class InsertMovieAdapterTest {

    private final InsertStudioAdapter insertStudioAdapter;
    private final InsertProducerAdapter insertProducerAdapter;
    private final InsertMovieAdapter insertMovieAdapter;
    private Movie movieToSave;
    private Movie duplicatedMovieToSave;
    private Studio associatedFilmDistribution;
    private Studio universalStudios;
    private Producer stevenSpielberg;
    private Producer joelSilver;
    private Producer matthewVaughn;

    @Autowired
    InsertMovieAdapterTest(InsertStudioAdapter insertStudioAdapter, InsertProducerAdapter insertProducerAdapter, InsertMovieAdapter insertMovieAdapter) {
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
                new LinkedHashSet<>(Set.of(associatedFilmDistribution, universalStudios)),
                new LinkedHashSet<>(Set.of(stevenSpielberg, joelSilver, matthewVaughn)),
                Boolean.TRUE
        );
        duplicatedMovieToSave = new Movie(
                UUID.randomUUID(),
                1990,
                "Movie Test",
                new LinkedHashSet<>(Set.of(associatedFilmDistribution)),
                new LinkedHashSet<>(Set.of(joelSilver)),
                null
        );
    }

    @Test
    @DisplayName("InsertMovieAdapter, InsertProducerAdapter e InsertStudioAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(insertMovieAdapter);
        assertNotNull(insertProducerAdapter);
        assertNotNull(insertStudioAdapter);
    }

    @Test
    @DisplayName("InsertMovieAdapter deve salvar uma entidade")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveMovie() {
        // When
        Arrays.asList(associatedFilmDistribution, universalStudios).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);
        Movie savedMovie = insertMovieAdapter.insert(movieToSave);

        // Then
        assertNotNull(savedMovie);
        assertNotNull(savedMovie.getId());
        assertEquals(movieToSave.getId(), savedMovie.getId());
        assertEquals(movieToSave.getYear(), savedMovie.getYear());
        assertEquals(movieToSave.getTitle(), savedMovie.getTitle());
        assertEquals(movieToSave.getStudios().size(), savedMovie.getStudios().size());
        assertEquals(movieToSave.getProducers().size(), savedMovie.getProducers().size());
        assertEquals(movieToSave.getWinner(), savedMovie.getWinner());
    }

    @Test
    @DisplayName("InsertMovieAdapter não deve salvar uma entidade com o campo 'year' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveMovieYearNull() {
        movieToSave.setYear(null);
        Arrays.asList(associatedFilmDistribution, universalStudios).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertMovieAdapter.insert(movieToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("InsertMovieAdapter não deve salvar uma entidade com o campo 'title' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveMovieNameNull() {
        movieToSave.setTitle(null);
        Arrays.asList(associatedFilmDistribution, universalStudios).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertMovieAdapter.insert(movieToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("MovieRepository não deve salvar uma entidade com os mesmos campos 'year' e 'title' de outra entidade")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveDuplicateMovieYearAndTitle() {
        // When
        Arrays.asList(associatedFilmDistribution, universalStudios).forEach(insertStudioAdapter::insert);
        Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn).forEach(insertProducerAdapter::insert);
        Movie savedMovie = insertMovieAdapter.insert(movieToSave);

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertMovieAdapter.insert(duplicatedMovieToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }
}