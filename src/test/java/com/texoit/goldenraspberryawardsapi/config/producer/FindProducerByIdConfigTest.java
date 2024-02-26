package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindProducerByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindProducerByIdConfigTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FindProducerByIdConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean FindProducerByIdUseCase foi criado corretamente")
    public void testFindProducerByIdUseCaseBeanCreation() {
        FindProducerByIdUseCase findProducerByIdUseCase = applicationContext.getBean(FindProducerByIdUseCase.class);
        assertNotNull(findProducerByIdUseCase);
    }
}
