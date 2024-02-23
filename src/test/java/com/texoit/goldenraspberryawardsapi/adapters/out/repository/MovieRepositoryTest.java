package com.texoit.goldenraspberryawardsapi.adapters.out.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MovieRepositoryTest {

    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    private MovieEntity movieToSave;
    private StudioEntity associatedFilmDistribution;
    private StudioEntity universalStudios;
    private ProducerEntity stevenSpielberg;
    private ProducerEntity joelSilver;
    private ProducerEntity matthewVaughn;


    @Autowired
    public MovieRepositoryTest(StudioRepository studioRepository, ProducerRepository producerRepository, MovieRepository movieRepository) {
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
        movieToSave = new MovieEntity(
                UUID.randomUUID(),
                1990,
                "Movie Test",
                new LinkedHashSet<>(Arrays.asList(associatedFilmDistribution, universalStudios)),
                new LinkedHashSet<>(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn)),
                Boolean.TRUE
        );
    }

    @Test
    @DisplayName("MovieRepository não deve ser nulo")
    public void repositoryLoads() {
        assertNotNull(movieRepository);
    }

    @Test
    @DisplayName("MovieRepository deve salvar uma entidade")
    public void testSaveMovie() {
        // When
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        MovieEntity savedMovie = movieRepository.saveAndFlush(movieToSave);

        // Then
        assertNotNull(savedMovie.getId());
        assertEquals(movieToSave.getYear(), savedMovie.getYear());
        assertEquals(movieToSave.getTitle(), savedMovie.getTitle());
        assertEquals(movieToSave.getStudios().size(), savedMovie.getStudios().size());
        assertEquals(movieToSave.getProducers().size(), savedMovie.getProducers().size());
        assertEquals(movieToSave.getWinner(), savedMovie.getWinner());
    }

    @Test
    @DisplayName("MovieRepository não deve salvar uma entidade com o campo 'year' nulo")
    public void testSaveMovieYearNull() {
        movieToSave.setYear(null);

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
                    producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
                    movieRepository.saveAndFlush(movieToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("MovieRepository não deve salvar uma entidade com o campo 'title' nulo")
    public void testSaveMovieNameNull() {
        movieToSave.setTitle(null);

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
                    producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
                    movieRepository.saveAndFlush(movieToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("MovieRepository não deve salvar uma entidade com os mesmos campos 'year' e 'title' de outra entidade")
    public void testSaveDuplicateMovieYearAndTitle() {
        // When
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        movieRepository.saveAndFlush(movieToSave);

        var duplicateMovie = new MovieEntity(
                UUID.randomUUID(),
                1990,
                "Movie Test",
                new LinkedHashSet<>(Arrays.asList(associatedFilmDistribution)),
                new LinkedHashSet<>(Arrays.asList(joelSilver)),
                null
        );

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> movieRepository.saveAndFlush(duplicateMovie));
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }

    @Test
    @DisplayName("MovieRepository deve recuperar uma entidade pelo campo 'id'")
    public void testFindByIdMovie() {
        // When
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        movieRepository.saveAndFlush(movieToSave);
        var retrievedMovie = movieRepository.getReferenceById(movieToSave.getId());

        // Then
        assertEquals(movieToSave.getId(), retrievedMovie.getId());
        assertEquals(movieToSave.getTitle(), retrievedMovie.getTitle());
    }
}
