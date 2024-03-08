package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
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
class FindProducerByIdAdapterTest {

    private final FindProducerByIdAdapter findProducerByIdAdapter;
    private final InsertProducerAdapter insertProducerAdapter;
    private Producer producerToInsert;

    @Autowired
    public FindProducerByIdAdapterTest(FindProducerByIdAdapter findProducerByIdAdapter, InsertProducerAdapter insertProducerAdapter) {
        this.findProducerByIdAdapter = findProducerByIdAdapter;
        this.insertProducerAdapter = insertProducerAdapter;
    }

    @BeforeEach
    public void setUp() {
        // Given
        producerToInsert = new Producer(
                UUID.randomUUID(),
                "Producer Test"
        );
    }

    @Test
    @DisplayName("FindProducerByIdAdapter e InsertProducerAdapter não devem ser nulos")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(findProducerByIdAdapter);
        assertNotNull(insertProducerAdapter);
    }

    @Test
    @DisplayName("FindProducerByIdAdapter deve recuperar uma classe de domínio pelo campo 'id'")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByIdProducer() {
        // When
        var insertedStudio = insertProducerAdapter.insert(producerToInsert);
        var retrievedProducer = findProducerByIdAdapter.find(insertedStudio.getId());

        // Then
        assertEquals(producerToInsert.getId(), retrievedProducer.getId());
        assertEquals(producerToInsert.getName(), retrievedProducer.getName());
    }
}