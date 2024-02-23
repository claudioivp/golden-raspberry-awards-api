package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.ProcessCSVFileUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProcessCSVFileConfigTest {

    private final ApplicationContext applicationContext;

    public ProcessCSVFileConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean ProcessCSVFileUseCase foi criado corretamente")
    public void testProcessCSVFileUseCaseBeanCreation() {
        ProcessCSVFileUseCase insertStudioUseCase = applicationContext.getBean(ProcessCSVFileUseCase.class);
        assertNotNull(insertStudioUseCase);
    }
}