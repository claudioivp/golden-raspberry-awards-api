package com.texoit.goldenraspberryawardsapi.adapters.out.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudioRepositoryTest {

    private final StudioRepository studioRepository;

    private StudioEntity studioToSave;

    @Autowired
    public StudioRepositoryTest(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @BeforeEach
    public void setUp() {
        // Given
        studioToSave = new StudioEntity();
        studioToSave.setId(UUID.randomUUID());
        studioToSave.setName("Studio Test");
    }

    @Test
    @DisplayName("StudioRepository não deve ser nulo")
    public void repositoryLoads() {
        assertNotNull(studioRepository);
    }

    @Test
    @DisplayName("StudioRepository deve salvar uma entidade")
    public void testSaveStudio() {
        // When
        var savedStudio = studioRepository.saveAndFlush(studioToSave);

        // Then
        assertNotNull(savedStudio.getId());
        assertEquals(studioToSave.getName(), savedStudio.getName());
    }

    @Test
    @DisplayName("StudioRepository não deve salvar uma entidade com o campo 'name' nulo")
    public void testSaveStudioNameNull() {
        //Given
        studioToSave.setName(null);

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    studioRepository.saveAndFlush(studioToSave);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("StudioRepository não deve salvar uma entidade com o mesmo campo 'name' de outra entidade")
    public void testSaveDuplicateStudioName() {
        // When
        studioRepository.saveAndFlush(studioToSave);

        var duplicateStudio = new StudioEntity();
        duplicateStudio.setName("Studio Test");

        // Then
        var dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> studioRepository.saveAndFlush(duplicateStudio));
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }

    @Test
    @DisplayName("StudioRepository deve recuperar uma entidade pelo campo 'id'")
    public void testFindByIdStudio() {
        // When
        studioRepository.saveAndFlush(studioToSave);
        var retrievedStudio = studioRepository.getReferenceById(studioToSave.getId());

        // Then
        assertEquals(studioToSave.getId(), retrievedStudio.getId());
        assertEquals(studioToSave.getName(), retrievedStudio.getName());
    }

    @Test
    @DisplayName("StudioRepository deve pesquisar uma entidade pelo campo 'name'")
    public void testFindByNameStudio() {
        // When
        studioRepository.save(studioToSave);
        Optional<StudioEntity> findStudioByNameOptional = studioRepository.findByName(studioToSave.getName());

        // Then
        assertTrue(findStudioByNameOptional.isPresent());
        var studioEntity = findStudioByNameOptional.get();
        assertEquals(studioToSave.getId(), studioEntity.getId());
        assertEquals(studioToSave.getName(), studioEntity.getName());
    }
}
