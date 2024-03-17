package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request.MovieProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request.MovieRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request.MovieStudioRequest;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class MovieControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    private MovieRequest movieRequestToSave;
    private MovieEntity movieEntityToSave;
    private StudioEntity associatedFilmDistribution;
    private StudioEntity universalStudios;
    private ProducerEntity stevenSpielberg;
    private ProducerEntity joelSilver;
    private ProducerEntity matthewVaughn;

    @Autowired
    public MovieControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, StudioRepository studioRepository, ProducerRepository producerRepository, MovieRepository movieRepository) {
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
                UUID.fromString("491948ec-fc10-42e5-a668-184ef1d7e687"),
                "Associated Film Distribution"
        );
        universalStudios = new StudioEntity(
                UUID.fromString("30cfba97-3ca4-4b9c-aa84-af36bd80eef4"),
                "Universal Studios"
        );

        // Given Producer
        stevenSpielberg = new ProducerEntity(
                UUID.fromString("c44cfb6d-fc3f-4ba7-aa60-e9b74175ae8f"),
                "Steven Spielberg"
        );
        joelSilver = new ProducerEntity(
                UUID.fromString("b1b794d4-a176-4cf9-a8d0-2be6fdaedeab"),
                "Joel Silver"
        );
        matthewVaughn = new ProducerEntity(
                UUID.fromString("5ca6f4f5-b4e3-4fa5-8e61-af60ce2fff07"),
                "Matthew Vaughn"
        );

        // Given Movie
        movieRequestToSave = new MovieRequest(
                1990,
                "Movie Test",
                new ArrayList<>(Arrays.asList(
                        new MovieStudioRequest(associatedFilmDistribution.getId()),
                        new MovieStudioRequest(universalStudios.getId()))
                ),
                new ArrayList<>(Arrays.asList(
                        new MovieProducerRequest(stevenSpielberg.getId()),
                        new MovieProducerRequest(joelSilver.getId()),
                        new MovieProducerRequest(matthewVaughn.getId()))
                ),
                Boolean.TRUE
        );
        movieEntityToSave = new MovieEntity(
                UUID.fromString("fc94b1fc-576c-4135-a660-8bdde22198aa"),
                movieRequestToSave.year(),
                movieRequestToSave.title(),
                new ArrayList<>(Arrays.asList(
                        associatedFilmDistribution,
                        universalStudios)
                ),
                new ArrayList<>(Arrays.asList(
                        stevenSpielberg,
                        joelSilver,
                        matthewVaughn)
                ),
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
    @DisplayName("MovieController deve inserir uma entidade a partir de um formulário MovieRequest e retornar 201 Created, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void insertionWorksThroughAllLayers() throws Exception {
        // Given
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));

        // When
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestToSave)))
                .andExpect(status().isCreated()) // Then
                .andExpect(jsonPath("$.id").isNotEmpty()) // Then
                .andExpect(jsonPath("$.year").value(movieRequestToSave.year())) // <Then>
                .andExpect(jsonPath("$.title").value(movieRequestToSave.title()))
                .andExpect(jsonPath(
                        "$.studios[?(@.id=='" + associatedFilmDistribution.getId().toString() +
                                "' && @.name=='" + associatedFilmDistribution.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.studios[?(@.id=='" + universalStudios.getId().toString() +
                                "' && @.name=='" + universalStudios.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + stevenSpielberg.getId().toString() +
                                "' && @.name=='" + stevenSpielberg.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + joelSilver.getId().toString() +
                                "' && @.name=='" + joelSilver.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + matthewVaughn.getId().toString() +
                                "' && @.name=='" + matthewVaughn.getName() + "')]").exists()
                )
                .andExpect(jsonPath("$.winner").value(movieRequestToSave.winner())); // </Then>

        // Then
        var findMovieByNameOptional = movieRepository.findByTitle(movieRequestToSave.title());
        assertTrue(findMovieByNameOptional.isPresent());
        var savedMovie = findMovieByNameOptional.get();
        assertNotNull(savedMovie);
        assertNotNull(savedMovie.getId());
        assertNotNull(savedMovie.getStudios());
        assertNotNull(savedMovie.getProducers());
        assertEquals(movieRequestToSave.year(), savedMovie.getYear());
        assertEquals(movieRequestToSave.title(), savedMovie.getTitle());
        assertEquals(movieRequestToSave.studios().size(), savedMovie.getStudios().size());
        var studios = savedMovie.getStudios();
        assertTrue(studios.stream().anyMatch(studio -> studio.getId().equals(associatedFilmDistribution.getId())));
        assertTrue(studios.stream().anyMatch(studio -> studio.getId().equals(universalStudios.getId())));
        assertEquals(movieRequestToSave.producers().size(), savedMovie.getProducers().size());
        var producers = savedMovie.getProducers();
        assertTrue(producers.stream().anyMatch(producer -> producer.getId().equals(stevenSpielberg.getId())));
        assertTrue(producers.stream().anyMatch(producer -> producer.getId().equals(joelSilver.getId())));
        assertTrue(producers.stream().anyMatch(producer -> producer.getId().equals(matthewVaughn.getId())));
        assertEquals(movieRequestToSave.winner(), savedMovie.getWinner());
    }

    @Test
    @DisplayName("MovieController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário MovieRequest com o campo 'year' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingNullYear() throws Exception {
        // Given
        movieRequestToSave = new MovieRequest(
                null,
                "Movie Title",
                new ArrayList<>(Arrays.asList(
                        new MovieStudioRequest(associatedFilmDistribution.getId()),
                        new MovieStudioRequest(universalStudios.getId()))
                ),
                new ArrayList<>(Arrays.asList(
                        new MovieProducerRequest(stevenSpielberg.getId()),
                        new MovieProducerRequest(joelSilver.getId()),
                        new MovieProducerRequest(matthewVaughn.getId()))
                ),
                Boolean.TRUE
        );

        // When
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'year')].message").value("O ano deve ser informado")); // Then
    }

    @Test
    @DisplayName("MovieController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário MovieRequest com o campo 'title' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingNullTitle() throws Exception {
        // Given
        movieRequestToSave = new MovieRequest(
                1990,
                null,
                new ArrayList<>(Arrays.asList(
                        new MovieStudioRequest(associatedFilmDistribution.getId()),
                        new MovieStudioRequest(universalStudios.getId()))
                ),
                new ArrayList<>(Arrays.asList(
                        new MovieProducerRequest(stevenSpielberg.getId()),
                        new MovieProducerRequest(joelSilver.getId()),
                        new MovieProducerRequest(matthewVaughn.getId()))
                ),
                Boolean.TRUE
        );

        // When
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'title')].message").value("O título não deve estar em branco")); // Then
    }



    @Test
    @DisplayName("MovieController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário MovieRequest com o campo 'studios' vazio")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingEmptyStudios() throws Exception {
        // Given
        movieRequestToSave = new MovieRequest(
                1990,
                "Movie Title",
                null,
                new ArrayList<>(Arrays.asList(
                        new MovieProducerRequest(stevenSpielberg.getId()),
                        new MovieProducerRequest(joelSilver.getId()),
                        new MovieProducerRequest(matthewVaughn.getId()))
                ),
                Boolean.TRUE
        );

        // When
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'studios')].message").value("Deve haver pelo menos um estúdio relacionado ao filme")); // Then
    }

    @Test
    @DisplayName("MovieController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário MovieRequest com o campo 'producers' vazio")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingEmptyProducers() throws Exception {
        // Given
        movieRequestToSave = new MovieRequest(
                1990,
                "Movie Title",
                new ArrayList<>(Arrays.asList(
                        new MovieStudioRequest(associatedFilmDistribution.getId()),
                        new MovieStudioRequest(universalStudios.getId()))
                ),
                null,
                Boolean.TRUE
        );

        // When
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'producers')].message").value("Deve haver pelo menos um produtor relacionado ao filme")); // Then
    }

    @Test
    @DisplayName("MovieController deve retornar 200 OK, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRetrieveMovie() throws Exception {
        // Given
        studioRepository.saveAllAndFlush(Arrays.asList(associatedFilmDistribution, universalStudios));
        producerRepository.saveAllAndFlush(Arrays.asList(stevenSpielberg, joelSilver, matthewVaughn));
        movieRepository.saveAndFlush(movieEntityToSave);

        List<ProducerEntity> all = producerRepository.findAll();

        // When
        mockMvc.perform(get("/api/v1/movies/{id}", movieEntityToSave.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Then
                .andExpect(jsonPath("$.id").isNotEmpty()) // Then
                .andExpect(jsonPath("$.year").value(movieEntityToSave.getYear())) // <Then>
                .andExpect(jsonPath("$.title").value(movieEntityToSave.getTitle()))
                .andExpect(jsonPath(
                        "$.studios[?(@.id=='" + associatedFilmDistribution.getId().toString() +
                                "' && @.name=='" + associatedFilmDistribution.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.studios[?(@.id=='" + universalStudios.getId().toString() +
                                "' && @.name=='" + universalStudios.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + stevenSpielberg.getId().toString() +
                                "' && @.name=='" + stevenSpielberg.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + joelSilver.getId().toString() +
                                "' && @.name=='" + joelSilver.getName() + "')]").exists()
                )
                .andExpect(jsonPath(
                        "$.producers[?(@.id=='" + matthewVaughn.getId().toString() +
                                "' && @.name=='" + matthewVaughn.getName() + "')]").exists()
                )
                .andExpect(jsonPath("$.winner").value(movieEntityToSave.getWinner())); // </Then>
    }

}