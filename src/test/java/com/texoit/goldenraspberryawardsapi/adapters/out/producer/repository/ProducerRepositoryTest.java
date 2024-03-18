package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.MovieRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProducerRepositoryTest {

    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    private ProducerEntity producerToSave;
    private ProducerIntervalsProjection expectedMinAwardIntervals;
    private ProducerIntervalsProjection expectedMaxAwardIntervals;
    private StudioEntity associatedFilmDistribution;
    private StudioEntity universalStudios;
    private StudioEntity unitedArtists;
    private ProducerEntity stevenSpielberg;
    private ProducerEntity joelSilver;
    private ProducerEntity matthewVaughn;
    private MovieEntity titanic;
    private MovieEntity xanadu;
    private MovieEntity revolution;
    private MovieEntity cobra;

    @Autowired
    public ProducerRepositoryTest(StudioRepository studioRepository, ProducerRepository producerRepository, MovieRepository movieRepository) {
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
        this.movieRepository = movieRepository;
    }

    @BeforeEach
    public void setUp() {
        // Given Producer
        producerToSave = new ProducerEntity(
                UUID.randomUUID(),
                "Producer Test"
        );

        // Given ProducerIntervalsProjection
        expectedMinAwardIntervals = new ProducerIntervalsProjection() {
            @Override
            public String getProducer() {
                return "Joel Silver";
            }
            @Override
            public Integer getYearInterval() {
                return 1;
            }
            @Override
            public Integer getPreviousWin() {
                return 1984;
            }
            @Override
            public Integer getFollowingWin() {
                return 1985;
            }
        };

        // Given ProducerIntervalsProjection
        expectedMaxAwardIntervals = new ProducerIntervalsProjection() {
            @Override
            public String getProducer() {
                return "Steven Spielberg";
            }
            @Override
            public Integer getYearInterval() {
                return 9;
            }
            @Override
            public Integer getPreviousWin() {
                return 1980;
            }
            @Override
            public Integer getFollowingWin() {
                return 1989;
            }
        };

        // Given Studios
        associatedFilmDistribution = new StudioEntity(
                UUID.randomUUID(),
                "Associated Film Distribution"
        );
        universalStudios = new StudioEntity(
                UUID.randomUUID(),
                "Universal Studios"
        );
        unitedArtists = new StudioEntity(
                UUID.randomUUID(),
                "United Artists"
        );

        // Given Producers
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

        //Given Movies
        titanic = new MovieEntity(
                UUID.randomUUID(),
                1980,
                "Titanic",
                new ArrayList<>(Collections.singletonList(associatedFilmDistribution)),
                new ArrayList<>(Collections.singletonList(stevenSpielberg)),
                Boolean.TRUE
        );
        xanadu = new MovieEntity(
                UUID.randomUUID(),
                1984,
                "Xanadu",
                new ArrayList<>(Collections.singletonList(universalStudios)),
                new ArrayList<>(Collections.singletonList(joelSilver)),
                Boolean.TRUE
        );
        revolution = new MovieEntity(
                UUID.randomUUID(),
                1985,
                "Revolution",
                new ArrayList<>(Collections.singletonList(unitedArtists)),
                new ArrayList<>(Collections.singletonList(joelSilver)),
                Boolean.TRUE
        );
        cobra = new MovieEntity(
                UUID.randomUUID(),
                1989,
                "Cobra",
                new ArrayList<>(Arrays.asList(associatedFilmDistribution, universalStudios)),
                new ArrayList<>(Arrays.asList(stevenSpielberg, matthewVaughn)),
                Boolean.TRUE
        );
    }

    @Test
    @DisplayName("ProducerRepository não deve ser nulo")
    public void repositoryLoads() {
        assertNotNull(producerRepository);
    }

    @Test
    @DisplayName("ProducerRepository deve salvar uma entidade")
    public void testSaveProducer() {
        // When
        var savedProducer = producerRepository.saveAndFlush(producerToSave);

        // Then
        assertNotNull(savedProducer.getId());
        assertEquals(producerToSave.getName(), savedProducer.getName());
    }

    @Test
    @DisplayName("ProducerRepository não deve salvar uma entidade com o campo 'name' nulo")
    public void testSaveProducerNameNull() {
        // Given
        producerToSave.setName(null);

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    producerRepository.saveAndFlush(producerToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("ProducerRepository não deve salvar uma entidade com o mesmo campo 'name' de outra entidade")
    public void testSaveDuplicateProducerName() {
        // When
        producerRepository.saveAndFlush(producerToSave);

        var duplicateProducer = new ProducerEntity();
        duplicateProducer.setName("Producer Test");

        // Then
        var dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> producerRepository.saveAndFlush(duplicateProducer));
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }

    @Test
    @DisplayName("ProducerRepository deve recuperar uma entidade pelo campo 'id'")
    public void testFindByIdProducer() {
        // When
        producerRepository.saveAndFlush(producerToSave);
        var retrievedProducer = producerRepository.getReferenceById(producerToSave.getId());

        // Then
        assertEquals(producerToSave.getId(), retrievedProducer.getId());
        assertEquals(producerToSave.getName(), retrievedProducer.getName());
    }

    @Test
    @DisplayName("ProducerRepository deve pesquisar uma entidade pelo campo 'name'")
    public void testFindByNameProducer() {
        // When
        producerRepository.save(producerToSave);
        Optional<ProducerEntity> findProducerByNameOptional = producerRepository.findByName(producerToSave.getName());

        // Then
        assertTrue(findProducerByNameOptional.isPresent());
        var producerEntity = findProducerByNameOptional.get();
        assertEquals(producerToSave.getId(), producerEntity.getId());
        assertEquals(producerToSave.getName(), producerEntity.getName());
    }

    @Test
    @DisplayName("ProducerRepository deve pesquisar o intervalo mínimo entre dois prêmios seguidos de um produtor")
    public void testFindMinIntervals() {
        // When
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution,universalStudios, unitedArtists));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        movieRepository.saveAll(Arrays.asList(titanic, xanadu, revolution, cobra));
        var findMinIntervals = producerRepository.findMinIntervals();

        // Then
        assertFalse(findMinIntervals.isEmpty());
        assertEquals(1, findMinIntervals.size());

        ProducerIntervalsProjection actualMinIntervals = findMinIntervals.iterator().next();
        assertEquals(expectedMinAwardIntervals.getProducer(), actualMinIntervals.getProducer());
        assertEquals(expectedMinAwardIntervals.getYearInterval(), actualMinIntervals.getYearInterval());
        assertEquals(expectedMinAwardIntervals.getPreviousWin(), actualMinIntervals.getPreviousWin());
        assertEquals(expectedMinAwardIntervals.getFollowingWin(), actualMinIntervals.getFollowingWin());
    }

    @Test
    @DisplayName("ProducerRepository deve pesquisar o intervalo máximo entre dois prêmios de um produtor")
    public void testFindMaxIntervals() {
        // When
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution,universalStudios, unitedArtists));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        movieRepository.saveAll(Arrays.asList(titanic, xanadu, revolution, cobra));
        var findMaxIntervals = producerRepository.findMaxIntervals();

        // Then
        assertFalse(findMaxIntervals.isEmpty());
        assertEquals(1, findMaxIntervals.size());

        ProducerIntervalsProjection actualMaxIntervals = findMaxIntervals.iterator().next();
        assertEquals(expectedMaxAwardIntervals.getProducer(), actualMaxIntervals.getProducer());
        assertEquals(expectedMaxAwardIntervals.getYearInterval(), actualMaxIntervals.getYearInterval());
        assertEquals(expectedMaxAwardIntervals.getPreviousWin(), actualMaxIntervals.getPreviousWin());
        assertEquals(expectedMaxAwardIntervals.getFollowingWin(), actualMaxIntervals.getFollowingWin());
    }
}
