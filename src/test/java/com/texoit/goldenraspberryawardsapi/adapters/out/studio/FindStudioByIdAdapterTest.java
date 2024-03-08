package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class FindStudioByIdAdapterTest {

    private final FindStudioByIdAdapter findStudioByIdAdapter;
    private final InsertStudioAdapter insertStudioAdapter;
    private Studio studioToInsert;

    @Autowired
    public FindStudioByIdAdapterTest(FindStudioByIdAdapter findStudioByIdAdapter, InsertStudioAdapter insertStudioAdapter) {
        this.findStudioByIdAdapter = findStudioByIdAdapter;
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
    @DisplayName("FindStudioByIdAdapter e InsertStudioAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(findStudioByIdAdapter);
        assertNotNull(insertStudioAdapter);
    }

    @Test
    @DisplayName("FindStudioByIdAdapter deve recuperar uma classe de domínio pelo campo 'id'")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByIdStudio() {
        // When
        var insertedStudio = insertStudioAdapter.insert(studioToInsert);
        var retrievedStudio = findStudioByIdAdapter.find(insertedStudio.getId());

        // Then
        assertEquals(studioToInsert.getId(), retrievedStudio.getId());
        assertEquals(studioToInsert.getName(), retrievedStudio.getName());
    }

}