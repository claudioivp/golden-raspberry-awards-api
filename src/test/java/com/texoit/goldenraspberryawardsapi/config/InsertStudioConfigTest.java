package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.InsertStudioUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InsertStudioConfigTest {

    private final ApplicationContext applicationContext;

    public InsertStudioConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean InsertStudioUseCase foi criado corretamente")
    public void testInsertStudioUseCaseBeanCreation() {
        InsertStudioUseCase insertStudioUseCase = applicationContext.getBean(InsertStudioUseCase.class);
        assertNotNull(insertStudioUseCase);
    }
}