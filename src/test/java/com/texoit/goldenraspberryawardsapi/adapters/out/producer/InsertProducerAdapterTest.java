package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
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
class InsertProducerAdapterTest {

    private final InsertProducerAdapter insertProducerAdapter;
    private Producer producerToInsert;

    @Autowired
    public InsertProducerAdapterTest(InsertProducerAdapter insertProducerAdapter) {
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
    @DisplayName("InsertProducerAdapter não deve ser nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void adapterLoads() {
        assertNotNull(insertProducerAdapter);
    }

    @Test
    @DisplayName("InsertProducerAdapter deve receber, inserir e retornar uma classe de domínio")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertProducer() {
        // When
        var insertedProducer = insertProducerAdapter.insert(producerToInsert);

        // Then
        assertNotNull(insertedProducer);
        assertNotNull(insertedProducer.getId());
        assertEquals(producerToInsert.getId(), insertedProducer.getId());
        assertEquals(producerToInsert.getName(), insertedProducer.getName());
    }

    @Test
    @DisplayName("InsertProducerAdapter não deve inserir uma classe de domínio com o campo 'name' nulo")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveProducerNameNull() {
        //Given
        producerToInsert.setName(null);

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    // When
                    insertProducerAdapter.insert(producerToInsert);
                });
        assertTrue(dataIntegrityViolationException.getMessage().contains("NULL not allowed for column"));
    }

    @Test
    @DisplayName("InsertProducerAdapter não deve inserir uma classe de domínio com o mesmo campo 'name' de outra")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSaveDuplicateProducerName() {
        // When
        insertProducerAdapter.insert(producerToInsert);

        var duplicateProducer = new Producer();
        duplicateProducer.setName("Producer Test");

        // Then
        var dataIntegrityViolationException = assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    //When
                    insertProducerAdapter.insert(duplicateProducer);
                }
        );
        assertTrue(dataIntegrityViolationException.getMessage().contains("Unique index or primary key violation"));
    }
}