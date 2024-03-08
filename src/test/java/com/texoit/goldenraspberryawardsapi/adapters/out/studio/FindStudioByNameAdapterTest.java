package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class FindStudioByNameAdapterTest {

    private final FindStudioByNameAdapter findStudioByNameAdapter;
    private final InsertStudioAdapter insertStudioAdapter;
    private Studio studioToInsert;

    @Autowired
    public FindStudioByNameAdapterTest(FindStudioByNameAdapter findStudioByNameAdapter, InsertStudioAdapter insertStudioAdapter) {
        this.findStudioByNameAdapter = findStudioByNameAdapter;
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
    @DisplayName("FindStudioByNameAdapter e InsertStudioAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(findStudioByNameAdapter);
        assertNotNull(insertStudioAdapter);
    }

    @Test
    @DisplayName("FindStudioByNameAdapter deve pesquisar uma entidade pelo campo 'name'")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByNameStudio() {
        // When
        insertStudioAdapter.insert(studioToInsert);
        var findStudioByNameOptional = findStudioByNameAdapter.find(studioToInsert.getName());

        // Then
        assertTrue(findStudioByNameOptional.isPresent());
        var studio = findStudioByNameOptional.get();
        assertEquals(studioToInsert.getId(), studio.getId());
        assertEquals(studioToInsert.getName(), studio.getName());
    }
}