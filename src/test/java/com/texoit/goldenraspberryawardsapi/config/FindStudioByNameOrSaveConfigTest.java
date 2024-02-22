package com.texoit.goldenraspberryawardsapi.config;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.FindStudioByNameOrSaveUseCase;
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
public class FindStudioByNameOrSaveConfigTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FindStudioByNameOrSaveConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean FindStudioByNameOrSaveUseCase foi criado corretamente")
    public void testFindStudioByNameOrSaveUseCaseBeanCreation() {
        FindStudioByNameOrSaveUseCase findStudioByNameOrSaveUseCase = applicationContext.getBean(FindStudioByNameOrSaveUseCase.class);
        assertNotNull(findStudioByNameOrSaveUseCase);
    }
}
