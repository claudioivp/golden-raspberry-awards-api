package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindMinAwardIntervalUseCase;
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
public class FindMinAwardIntervalConfigTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FindMinAwardIntervalConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean FindMinAwardIntervalUseCase foi criado corretamente")
    public void testFindMinAwardIntervalUseCaseBeanCreation() {
        FindMinAwardIntervalUseCase findMinAwardIntervalUseCase = applicationContext.getBean(FindMinAwardIntervalUseCase.class);
        assertNotNull(findMinAwardIntervalUseCase);
    }
}
