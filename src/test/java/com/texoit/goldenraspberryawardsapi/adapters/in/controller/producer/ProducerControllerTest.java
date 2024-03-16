package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.mapper.ProducerMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class ProducerControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ProducerRepository producerRepository;
    private ProducerRequest producerRequestToSave;
    private ProducerEntity producerEntityToSave;

    @Autowired
    public ProducerControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ProducerRepository producerRepository, ProducerMapper producerMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.producerRepository = producerRepository;
    }

    @BeforeEach
    public void setUp() {
        // Given
        producerRequestToSave = new ProducerRequest(
                "Producer Test"
        );
        producerEntityToSave = new ProducerEntity(
                UUID.fromString("c8631ba5-e9e5-491b-b689-cf11ff4191e5"),
                producerRequestToSave.name()
        );
    }

    @Test
    @DisplayName("MockMvc, ObjectMapper, InsertProducerAdapter, FindProducerByNameAdapter e ProducerMapper não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDependenciesNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(producerRepository);
    }

    @Test
    @DisplayName("ProducerController deve inserir uma entidade a partir de um formulário ProducerRequest e retornar 201 Created, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void insertionWorksThroughAllLayers() throws Exception {
        // When
        mockMvc.perform(post("/api/v1/producers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producerRequestToSave)))
                .andExpect(status().isCreated()) // Then
                .andExpect(jsonPath("$.id").isNotEmpty()) // Then
                .andExpect(jsonPath("$.name").value(producerEntityToSave.getName())); // Then

        // Then
        var findProducerByNameOptional = producerRepository.findByName(producerRequestToSave.name());
        assertTrue(findProducerByNameOptional.isPresent());
        var savedProducer = findProducerByNameOptional.get();
        assertThat(savedProducer.getName()).isEqualTo(producerRequestToSave.name());
    }

    @Test
    @DisplayName("ProducerController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário ProducerRequest com o campo 'name' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingNullName() throws Exception {
        // Given
        producerRequestToSave = new ProducerRequest(null);

        // When
        mockMvc.perform(post("/api/v1/producers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producerRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'name')].message").value("O nome não deve estar em branco")); // Then
    }

    @Test
    @DisplayName("ProducerController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário ProducerRequest com o mesmo campo 'name' de outra")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingInvalidData() throws Exception {
        // Given
        producerRepository.save(producerEntityToSave);

        // When
        mockMvc.perform(post("/api/v1/producers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producerRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors.message").value("Falha na integridade dos dados")); // Then
    }

    @Test
    @DisplayName("ProducerController deve retornar 200 OK, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRetrieveProducer() throws Exception {
        // Given
        producerRepository.save(producerEntityToSave);

        // When
        mockMvc.perform(get("/api/v1/producers/{id}", producerEntityToSave.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Then
                .andExpect(jsonPath("$.id").value(producerEntityToSave.getId().toString())) // Then
                .andExpect(jsonPath("$.name").value(producerEntityToSave.getName())); // Then
    }

}