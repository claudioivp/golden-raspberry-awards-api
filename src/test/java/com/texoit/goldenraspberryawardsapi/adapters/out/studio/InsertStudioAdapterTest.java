package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class InsertStudioAdapterTest {

    private final InsertStudioAdapter insertStudioAdapter;

    private Studio studioToInsert;

    @Autowired
    public InsertStudioAdapterTest(InsertStudioAdapter insertStudioAdapter) {
        this.insertStudioAdapter = insertStudioAdapter;
    }

    @BeforeEach
    public void setUp() {
        // Given
        studioToInsert = new Studio(
                UUID.randomUUID(),
                "Studio Test"
        );
    }

    @Test
    @DisplayName("InsertStudioAdapter não deve ser nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(insertStudioAdapter);
    }

    @Test
    @DisplayName("InsertStudioAdapter deve receber, inserir e retornar uma classe de domínio")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertStudio() {
        // When
        var insertedStudio = insertStudioAdapter.insert(studioToInsert);

        // Then
        assertNotNull(insertedStudio);
        assertNotNull(insertedStudio.getId());
        assertEquals(studioToInsert.getId(), insertedStudio.getId());
        assertEquals(studioToInsert.getName(), insertedStudio.getName());
    }

    @Test
    @DisplayName("InsertStudioAdapter não deve inserir uma classe de domínio com o campo 'name' nulo")
    public void testSaveStudioNameNull() {
        //Given
        studioToInsert.setName(null);

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertStudioAdapter.insert(studioToInsert);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("InsertStudioAdapter não deve inserir uma classe de domínio com o mesmo campo 'name' de outra")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveDuplicateStudioName() {
        // When
        insertStudioAdapter.insert(studioToInsert);

        var duplicateStudio = new Studio();
        duplicateStudio.setName("Studio Test");

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertStudioAdapter.insert(duplicateStudio);
                }
        );
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }

}