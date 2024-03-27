package com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.mapper.StudioMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.request.StudioRequest;
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
public class StudioControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final StudioRepository studioRepository;
    private StudioRequest studioRequestToSave;
    private StudioEntity studioEntityToSave;

    @Autowired
    public StudioControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, StudioRepository studioRepository, StudioMapper studioMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.studioRepository = studioRepository;
    }

    @BeforeEach
    public void setUp() {
        // Given
        studioRequestToSave = new StudioRequest(
                "Studio Test"
        );
        studioEntityToSave = new StudioEntity(
                UUID.fromString("c8631ba5-e9e5-491b-b689-cf11ff4191e5"),
                studioRequestToSave.name()
        );
    }

    @Test
    @DisplayName("MockMvc, ObjectMapper, InsertStudioAdapter, FindStudioByNameAdapter e StudioMapper não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDependenciesNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(studioRepository);
    }

    @Test
    @DisplayName("StudioController deve inserir uma entidade a partir de um formulário StudioRequest e retornar 201 Created, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void insertionWorksThroughAllLayers() throws Exception {
        // When
        mockMvc.perform(post("/api/v1/studios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studioRequestToSave)))
                .andExpect(status().isCreated()) // Then
                .andExpect(jsonPath("$.id").isNotEmpty()) // Then
                .andExpect(jsonPath("$.name").value(studioEntityToSave.getName())); // Then

        // Then
        var findStudioByNameOptional = studioRepository.findByName(studioRequestToSave.name());
        assertTrue(findStudioByNameOptional.isPresent());
        var savedStudio = findStudioByNameOptional.get();
        assertThat(savedStudio.getName()).isEqualTo(studioRequestToSave.name());
    }

    @Test
    @DisplayName("StudioController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário StudioRequest com o campo 'name' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingNullName() throws Exception {
        // Given
        studioRequestToSave = new StudioRequest(null);

        // When
        mockMvc.perform(post("/api/v1/studios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studioRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors[?(@.field == 'name')].message").value("O nome não deve estar em branco")); // Then
    }

    @Test
    @DisplayName("StudioController deve retornar 400 Bad Request ao tentar inserir uma entidade a partir de um formulário StudioRequest com o mesmo campo 'name' de outra")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBadRequestWhenInsertingInvalidData() throws Exception {
        // Given
        studioRepository.save(studioEntityToSave);

        // When
        mockMvc.perform(post("/api/v1/studios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studioRequestToSave)))
                .andExpect(status().isBadRequest()) // Then
                .andExpect(jsonPath("$.errors.message").value("Falha na integridade dos dados. Já existe um registro com os mesmos dados fornecidos.")); // Then
    }

    @Test
    @DisplayName("StudioController deve retornar 200 OK, além da entidade persistida")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRetrieveStudio() throws Exception {
        // Given
        studioRepository.save(studioEntityToSave);

        // When
        mockMvc.perform(get("/api/v1/studios/{id}", studioEntityToSave.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Then
                .andExpect(jsonPath("$.id").value(studioEntityToSave.getId().toString())) // Then
                .andExpect(jsonPath("$.name").value(studioEntityToSave.getName())); // Then
    }

}