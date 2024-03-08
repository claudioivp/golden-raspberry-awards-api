package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class FindProducerByNameAdapterTest {

    private final FindProducerByNameAdapter findProducerByNameAdapter;
    private final InsertProducerAdapter insertProducerAdapter;
    private Producer producerToInsert;

    @Autowired
    public FindProducerByNameAdapterTest(FindProducerByNameAdapter findProducerByNameAdapter, InsertProducerAdapter insertProducerAdapter) {
        this.findProducerByNameAdapter = findProducerByNameAdapter;
        this.insertProducerAdapter = insertProducerAdapter;
    }

    @BeforeEach
    void setUp() {
        // Given
        producerToInsert = new Producer(
                UUID.randomUUID(),
                "Producer Test"
        );
    }

    @Test
    @DisplayName("FindProducerByNameAdapter e InsertProducerAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(findProducerByNameAdapter);
        assertNotNull(insertProducerAdapter);
    }

    @Test
    @DisplayName("FindProducerByNameAdapter deve pesquisar uma entidade pelo campo 'name'")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByNameProducer() {
        // When
        insertProducerAdapter.insert(producerToInsert);
        var findProducerByNameOptional = findProducerByNameAdapter.find(producerToInsert.getName());

        // Then
        assertTrue(findProducerByNameOptional.isPresent());
        var producer = findProducerByNameOptional.get();
        assertEquals(producerToInsert.getId(), producer.getId());
        assertEquals(producerToInsert.getName(), producer.getName());
    }
}