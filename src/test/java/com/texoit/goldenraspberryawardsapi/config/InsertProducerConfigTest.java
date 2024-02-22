package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.InsertProducerUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InsertProducerConfigTest {

    private final ApplicationContext applicationContext;

    public InsertProducerConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean InsertProducerUseCase foi criado corretamente")
    public void testInsertProducerUseCaseBeanCreation() {
        InsertProducerUseCase insertProducerUseCase = applicationContext.getBean(InsertProducerUseCase.class);
        assertNotNull(insertProducerUseCase);
    }
}