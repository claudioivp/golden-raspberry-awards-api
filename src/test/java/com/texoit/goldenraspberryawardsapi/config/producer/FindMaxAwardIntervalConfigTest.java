package com.texoit.goldenraspberryawardsapi.config.producer;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.producer.FindMaxAwardIntervalUseCase;
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
public class FindMaxAwardIntervalConfigTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FindMaxAwardIntervalConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean FindMaxAwardIntervalUseCase foi criado corretamente")
    public void testFindMaxAwardIntervalUseCaseBeanCreation() {
        FindMaxAwardIntervalUseCase findMaxAwardIntervalUseCase = applicationContext.getBean(FindMaxAwardIntervalUseCase.class);
        assertNotNull(findMaxAwardIntervalUseCase);
    }
}
